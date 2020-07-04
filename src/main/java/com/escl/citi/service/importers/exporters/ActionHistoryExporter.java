package com.escl.citi.service.importers.exporters;

import com.escl.citi.entity.ActionHistory;
import com.escl.citi.entity.User;
import com.escl.citi.persistence.repository.ActionHistoryRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ActionHistoryExporter extends Exporter {

    private static final String FILE_NAME = "historia_czynności.xlsx";

    private ActionHistoryRepository actionHistoryRepository;

    public ActionHistoryExporter(ActionHistoryRepository actionHistoryRepository) {
        this.actionHistoryRepository = actionHistoryRepository;
    }

    public void xlsxExport(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException {

        Iterable<ActionHistory> actionHistoryList;
        if (!request.getParameter("startDate").isEmpty() && !request.getParameter("endDate").isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(request.getParameter("startDate"));
            Date endDate = formatter.parse(request.getParameter("endDate"));

            actionHistoryList = actionHistoryRepository.findAllBetweenDatesForExport(startDate, endDate);
        } else {
            actionHistoryList = actionHistoryRepository.findAllByOrderByIdDesc();
        }


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Historia czynności");

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        cellStyleHeader.setFont(font);

        XSSFCellStyle cellDateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-m-d h:mm:ss"));
        cellDateStyle.setAlignment(HorizontalAlignment.CENTER);

        int rowNum = 0;
        int colNum = 0;

        Row row = sheet.createRow(rowNum++);

        sheet.setColumnWidth(colNum, 10000);
        Cell cell = row.createCell(colNum++);
        cell.setCellValue("Użytkownik");
        cell.setCellStyle(cellStyleHeader);

        sheet.setColumnWidth(colNum, 16000);
        cell = row.createCell(colNum++);
        cell.setCellValue("Akcja");
        cell.setCellStyle(cellStyleHeader);

        sheet.setColumnWidth(colNum, 4000);
        cell = row.createCell(colNum++);
        cell.setCellValue("Data");
        cell.setCellStyle(cellStyleHeader);

        for (ActionHistory actionHistory : actionHistoryList) {
            colNum = 0;

            row = sheet.createRow(rowNum++);
            cell = row.createCell(colNum++);

            cell.setCellValue(actionHistory.getUser().getFullName());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(colNum++);

            cell.setCellValue(actionHistory.getActionName());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(colNum++);

            cell.setCellValue(actionHistory.getCreateDate());
            cell.setCellStyle(cellDateStyle);
        }

        write(response, workbook, FILE_NAME);
    }
}
