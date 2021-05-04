package com.example.demo.response;

import java.util.List;
import java.util.Map;

public class EnterpriseGetRowsResponse {
    private Boolean success;
    private List<Map<String, Object>> data;
    private int lastRow;
    private List<String> secondaryColumnFields;

    public EnterpriseGetRowsResponse() { }

    public EnterpriseGetRowsResponse(List<Map<String, Object>> data, int lastRow,Boolean success, List<String> secondaryColumnFields) {
        this.data = data;
        this.lastRow = lastRow;
        this.secondaryColumnFields = secondaryColumnFields;
        this.success = success;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public List<String> getSecondaryColumnFields() {
        return secondaryColumnFields;
    }

    public void setSecondaryColumns(List<String> secondaryColumnFields) {
        this.secondaryColumnFields = secondaryColumnFields;
    }
}