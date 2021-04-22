package com.speedway.demo.model;

import com.speedway.demo.racecar.RaceCarDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String nickName;
    @Embedded
    private List<RaceCarDTO> cars;
    private int wins;
    private int loses;

    public DriverEntity(String firstName, String lastName, int age, String nickName, List<RaceCarDTO> cars, int wins, int loses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nickName = nickName;
        this.cars = cars;
        this.wins = wins;
        this.loses = loses;
    }
}

