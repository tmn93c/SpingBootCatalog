package com.example.demo.response;


public class CustomerResponse {
    private String customer_id;
    private String name;
  
    public String getId() {
        return customer_id;
    }

    public void setId(String customer_id) {
        this.customer_id = customer_id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
