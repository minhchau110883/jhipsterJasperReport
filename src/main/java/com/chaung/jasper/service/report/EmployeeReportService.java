package com.chaung.jasper.service.report;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.chaung.jasper.domain.Employee;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeReportService {

    public byte[] getReportXlsx(final JasperPrint jp) throws JRException, IOException {
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        final byte[] rawBytes;

        try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){
            xlsxExporter.setExporterInput(new SimpleExporterInput(jp));
            xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
            xlsxExporter.exportReport();

            rawBytes = xlsReport.toByteArray();
        }

        return rawBytes;
    }

    public JasperPrint getReport(List<Employee> employeeList) throws ColumnBuilderException, JRException, ClassNotFoundException {
        Style headerStyle = createHeaderStyle();
        Style detailTextStyle = createDetailTextStyle();
        Style detailNumberStyle = createDetailNumberStyle();
        DynamicReport dynaReport = getReport(headerStyle, detailTextStyle, detailNumberStyle);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(),
            new JRBeanCollectionDataSource(employeeList));
        return jp;
    }

    private Style createHeaderStyle() {
        return new StyleBuilder(true)
            .setFont(Font.VERDANA_MEDIUM_BOLD)
            .setBorder(Border.THIN())
            .setBorderBottom(Border.PEN_2_POINT())
            .setBorderColor(Color.BLACK)
            .setBackgroundColor(Color.ORANGE)
            .setTextColor(Color.BLACK)
            .setHorizontalAlign(HorizontalAlign.CENTER)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setTransparency(Transparency.OPAQUE)
            .build();
    }

    private Style createDetailTextStyle() {
        return new StyleBuilder(true)
            .setFont(Font.VERDANA_MEDIUM)
            .setBorder(Border.DOTTED())
            .setBorderColor(Color.BLACK)
            .setTextColor(Color.BLACK)
            .setHorizontalAlign(HorizontalAlign.LEFT)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setPaddingLeft(5)
            .build();
    }

    private Style createDetailNumberStyle() {
        return new StyleBuilder(true)
            .setFont(Font.VERDANA_MEDIUM)
            .setBorder(Border.DOTTED())
            .setBorderColor(Color.BLACK)
            .setTextColor(Color.BLACK)
            .setHorizontalAlign(HorizontalAlign.RIGHT)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setPaddingRight(5)
            .setPattern("#,##0.00")
            .build();
    }

    private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle)
        throws ColumnBuilderException, ClassNotFoundException {

        DynamicReportBuilder report = new DynamicReportBuilder();

        AbstractColumn columnEmpNo = createColumn("id", Long.class, "Employee Number", 30, headerStyle, detailTextStyle);
        AbstractColumn columnName = createColumn("name", String.class, "Name", 30, headerStyle, detailTextStyle);
        AbstractColumn columnSalary = createColumn("salary", Integer.class, "Salary", 30, headerStyle, detailNumStyle);
        AbstractColumn columnCommission = createColumn("commission", Float.class, "Commission", 30, headerStyle, detailNumStyle);
        report.addColumn(columnEmpNo).addColumn(columnName).addColumn(columnSalary).addColumn(columnCommission);

        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, null, true));
        // you can also specify a font from the classpath, eg:
        // titleStyle.setFont(new Font(20, "/fonts/someFont.ttf", true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, null, true));

        report.setTitle("Employee Report");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle("Commission received by Employee");
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true);
        return report.build();
    }

    private AbstractColumn createColumn(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle)
        throws ColumnBuilderException {
        return ColumnBuilder.getNew()
            .setColumnProperty(property, type.getName())
            .setTitle(title)
            .setWidth(Integer.valueOf(width))
            .setStyle(detailStyle)
            .setHeaderStyle(headerStyle)
            .build();
    }
}
