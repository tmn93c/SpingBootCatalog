package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;


public class TradeResponse {
    private Long id;
    private String name;
    private Long current_value;
    private Long previous_value;
    private Long pl1;
    private Long pl2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoice;
    private Long totalVotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getcurrent_value() {
        return current_value;
    }

    public void setcurrent_value(Long current_value) {
        this.current_value = current_value;
    }
    public Long getprevious_value() {
        return previous_value;
    }

    public void setprevious_value(Long previous_value) {
        this.previous_value = previous_value;
    }
    public Long getPl1() {
        return pl1;
    }

    public void setPl1(Long pl1) {
        this.pl1 = pl1;
    }
    public Long getPl2() {
        return pl2;
    }

    public void setPl2(Long pl2) {
        this.pl2 = pl2;
    }

    public Long getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(Long selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }
}
