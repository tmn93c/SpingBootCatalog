package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity(name = "@EntityTrade")
@Table(name = "trade")
public class TradeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private Long current_value;

    private Long previous_value;

    private Long pl1;

    private Long pl2;

    @NotBlank
    @Size(max = 140)
    private String name;
    

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
}

    