package com.example.demo.util;
import java.io.IOException;
import java.util.*;
public class MainExcel {
    public static void main(String[] args) {
        ExcelUtil excelUtil = new ExcelUtil();

        // Define headers
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("id", "ID");
        headers.put("name", "Name");
        headers.put("age", "Age");

        // Define body data
        List<Map<String, Object>> body = new ArrayList<>();
        body.add(new HashMap<String, Object>() {{
            put("id", 1);
            put("name", "Alice");
            put("age", 30);
        }});
        body.add(new HashMap<String, Object>() {{
            put("id", 2);
            put("name", "Bob");
            put("age", 25);
        }});

        // Create Excel file
        try {
            excelUtil.createExcelFile(headers, body, "output.xlsx");
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
