package com.example.demo.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TradeRequest {
  
    @NotBlank
    @Size(min = 3, max = 15)
    private String name;
  
    private Long current_value;

    private Long previous_value;

    private Long pl1;

    private Long pl2;

    // @NotNull(message = "luser cannot be null")
    // private List<UserDTO> user;
  
    
  
    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public Long getprevious_value() {
        return previous_value;
    }
  
    public void setprevious_value(Long previous_value) {
        this.previous_value = previous_value;
    }

    public Long getCurrent_value() {
        return current_value;
    }
  
    public void setCurrent_value(Long current_value) {
        this.current_value = current_value;
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