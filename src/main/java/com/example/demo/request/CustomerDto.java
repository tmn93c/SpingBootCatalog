package com.example.demo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDto {

    String name;
    Integer age;
    String branch;
}