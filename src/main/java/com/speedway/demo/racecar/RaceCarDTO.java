package com.speedway.demo.racecar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceCarDTO {
    private String nickname;
    private String model;
    private int year;
    private int owner;
    private String status;
    private int top_speed;
}
