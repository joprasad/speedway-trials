package com.speedway.demo.model;

import com.speedway.demo.racecar.RaceCarDTO;
import com.speedway.demo.racecar.RaceCarEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String nickName;
    @OneToMany(mappedBy = "driverEntity",cascade = CascadeType.ALL)
    private List<RaceCarEntity> cars;
    private int wins;
    private int loses;

    public DriverEntity(String firstName, String lastName, int age, String nickName, List<RaceCarEntity> cars, int wins, int loses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nickName = nickName;
        this.cars = cars;
        this.wins = wins;
        this.loses = loses;
    }
}

