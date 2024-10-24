package com.example.demo.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    /**
     * Creates an Excel file with the given headers and data.
     *
     * @param headers a Map containing the header names and their corresponding column keys
     * @param body    a List of Maps where each Map represents a row of data
     * @param filePath the path to save the Excel file
     * @throws IOException if an I/O error occurs
     */
    public void createExcelFile(Map<String, String> headers, List<Map<String, Object>> body, String filePath) throws IOException {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            int headerIndex = 0;
            for (String header : headers.values()) {
                Cell cell = headerRow.createCell(headerIndex++);
                cell.setCellValue(header);
            }

            // Create body rows
            int rowIndex = 1;
            for (Map<String, Object> rowData : body) {
                Row row = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (String key : headers.keySet()) {
                    Cell cell = row.createCell(cellIndex++);
                    Object value = rowData.get(key);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Auto-size columns
            for (int i = 0; i < headers.size(); i++) {
                int maxWidth = Math.max(headers.get(headers.keySet().toArray()[i]).length(),
                        getMaxColumnWidth(body, headers.keySet().toArray()[i]));
                sheet.setColumnWidth(i, maxWidth * 256); // Multiply by 256 to get the correct width in units
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }

    /**
     * Gets the maximum width of the data in a specific column.
     *
     * @param body  the body data
     * @param key   the key of the column to check
     * @return the maximum width of the column
     */
    private int getMaxColumnWidth(List<Map<String, Object>> body, Object key) {
        int maxWidth = 0;
        for (Map<String, Object> rowData : body) {
            Object value = rowData.get(key);
            if (value != null) {
                maxWidth = Math.max(maxWidth, value.toString().length());
            }
        }
        return maxWidth;
    }
}