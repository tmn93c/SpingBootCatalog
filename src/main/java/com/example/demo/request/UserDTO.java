package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotNull(message = "Name cannot be null")
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;
  
 
    @NotBlank
    private Integer currentValue;
  
    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public Integer getCurrentValue() {
        return currentValue;
    }
  
    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }
}