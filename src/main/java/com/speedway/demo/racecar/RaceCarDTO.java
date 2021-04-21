package com.speedway.demo.racecar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceCarDTO {
    private Long id;
    private String nickname;
    private String model;
    private int year;
    private int owner;
    private String status;
    private int top_speed;

    public RaceCarDTO(String nickname, String model, int year, int owner, String status, int top_speed) {
        this.nickname = nickname;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.status = status;
        this.top_speed = top_speed;
    }
}
