package com.escl.citi.service.importers.exporters;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract class Exporter {

    public XSSFCellStyle cellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellStyle;
    }

    public XSSFCellStyle dateStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellDateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-m-d h:mm:ss"));
        cellDateStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellDateStyle;
    }

    public XSSFCellStyle headerStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        cellStyleHeader.setFont(font);

        return cellStyleHeader;
    }

    public void write(HttpServletResponse response, XSSFWorkbook workbook, String fileName) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition",
                    String.format("attachment; filename=\"%s\"", fileName));
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
