package com.speedway.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private String firstName;
    private String lastName;
    private int age;
    private String nickName;
    private String cars;
    private int wins;
    private int loses;
}
