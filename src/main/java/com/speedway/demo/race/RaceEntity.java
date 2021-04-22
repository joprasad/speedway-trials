package com.speedway.demo.race;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String category;
    LocalDate date;
    String bestTime;
    Long winner;
    List<Integer> participants;

    public RaceEntity(String name, String category, LocalDate date, String bestTime, Long winner, List<Integer> participants) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.bestTime = bestTime;
        this.winner = winner;
        this.participants = participants;
    }
}
