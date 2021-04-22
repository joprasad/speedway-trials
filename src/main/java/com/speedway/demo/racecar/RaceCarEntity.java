package com.speedway.demo.racecar;

import com.speedway.demo.model.DriverEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceCarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String model;
    private int year;
    private int owner;
    private String status;
    private int top_speed;
    @ManyToOne
    @JoinColumn(name = "d_id")
    private DriverEntity driverEntity;

    public RaceCarEntity(String nickname, String model, int year, int owner, String status, int top_speed) {
        this.nickname = nickname;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.status = status;
        this.top_speed = top_speed;
    }

    public RaceCarEntity(Long id, String nickname, String model, int year, int owner, String status, int top_speed) {
        this.id = id;
        this.nickname = nickname;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.status = status;
        this.top_speed = top_speed;
    }
}
