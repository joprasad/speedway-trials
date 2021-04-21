package com.speedway.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String cars;
    private int wins;
    private int loses;

    public DriverEntity(String firstName, String lastName, int age, String nickName, String cars, int wins, int loses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nickName = nickName;
        this.cars = cars;
        this.wins = wins;
        this.loses = loses;
    }
}

