package com.example.demo.payload;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class GetTradeByIdsRequest {
    @NotBlank
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
