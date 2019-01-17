package com.chaung.jasper.service.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XlsxReportService {

    public static byte[] getReportXlsx(final JasperPrint jp) throws JRException, IOException {
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        final byte[] rawBytes;

        xlsxExporter.getJasperReportsContext().getProperties();
        try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){
            xlsxExporter.setExporterInput(new SimpleExporterInput(jp));
            xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
            //xlsxExporter.getJasperReportsContext().
            xlsxExporter.exportReport();

            rawBytes = xlsReport.toByteArray();
        }

        return rawBytes;
    }
}
