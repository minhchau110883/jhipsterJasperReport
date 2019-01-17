package com.chaung.jasper.web.rest;

import com.chaung.jasper.domain.DataBean;
import com.chaung.jasper.domain.DataBeanList;
import com.chaung.jasper.domain.Employee;
import com.chaung.jasper.service.EmployeeService;
import com.chaung.jasper.service.report.EmployeeReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class EmployeeReportResource {

    private final EmployeeReportService employeeReportService;
    private final EmployeeService employeeService;

    private final ResourceLoader resourceLoader;
    //private final JasperFillManager jasperFillManager;

    public EmployeeReportResource(EmployeeReportService employeeReportService,
                                  EmployeeService employeeService,
                                  ResourceLoader resourceLoader) {
        this.employeeReportService = employeeReportService;
        this.employeeService = employeeService;
        this.resourceLoader = resourceLoader;
        //this.jasperFillManager = jasperFillManager;
    }

    @GetMapping(value = "/employeeReport.xlsx", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportXlsx(final HttpServletResponse response) throws JRException, IOException, ClassNotFoundException {

        List<Employee> employeesList = employeeService.findAll();
        JasperPrint jasperPrint = employeeReportService.getReport(employeesList);
        final byte[] data = employeeReportService.getReportXlsx(jasperPrint);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.xlsx");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }

    @GetMapping(value = "/exporting")
    public HttpEntity<byte[]> getEmployeeReportXlsx() throws Exception {

        //String sourceFileName = "classpath:templates/report/jasper_report_template_design.jrxml";
        String sourceFileName = resourceLoader.getResource("classpath:templates/report/jasper_report_template_design.jrxml").getURI().getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
        DataBeanList DataBeanList = new DataBeanList();
        ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
        JRBeanCollectionDataSource beanColDataSource =
            new JRBeanCollectionDataSource(dataList);

        Map parameters = new HashMap();
        parameters.put("reportTitle", "List of Contacts");
        parameters.put("author", "Prepared By Chau Ng");

        try {
           // JasperFillManager jasperFillManager = new JasperFillManager();
            JasperPrint jasperPrint = JasperFillManager.getInstance(DefaultJasperReportsContext.getInstance()).fill(jasperReport, parameters, beanColDataSource);

            final byte[] data = employeeReportService.getReportXlsx(jasperPrint);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.xlsx");
            header.setContentLength(data.length);

            return new HttpEntity<byte[]>(data, header);

            //if (printFileName != null) {
                /**
                 * 1- export to PDF
                 */
                //JasperExportManager.exportReportToPdfFile(printFileName, "classpath:templates/report/sample_report.pdf");

                /**
                 * 2- export to HTML
                 */
                //JasperExportManager.exportReportToHtmlFile(printFileName, "C://sample_report.html");

                /**
                 * 3- export to Excel sheet
                 */
//                JRXlsExporter exporter = new JRXlsExporter();
//
//                exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C://sample_report.xls");
//
//                exporter.exportReport();
            //}
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
}
