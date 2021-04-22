package com.speedway.demo.race;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceDTO {
    String name;
    String category;
    LocalDate date;
    String bestTime;
    Long winner;
    List<Integer> participants;
}
