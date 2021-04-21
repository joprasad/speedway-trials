package com.speedway.demo.model;

import com.speedway.demo.racecar.RaceCarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private String firstName;
    private String lastName;
    private int age;
    private String nickName;
    private List<RaceCarDTO> cars;
    private int wins;
    private int loses;
}
