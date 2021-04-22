package com.speedway.demo.driver;


import com.speedway.demo.model.Driver;
import com.speedway.demo.model.DriverEntity;
import com.speedway.demo.racecar.RaceCarDTO;
import com.speedway.demo.racecar.RaceCarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public void saveDriver(Driver driver) {

        driverRepository.save(new DriverEntity(
                driver.getFirstName(),
                driver.getLastName(),
                driver.getAge(),
                driver.getNickName(),
                driver.getCars().stream().map(raceCar ->
                        new RaceCarEntity(raceCar.getId(),raceCar.getNickname(),
                                raceCar.getModel(),
                                raceCar.getYear(),
                                raceCar.getOwner(),
                                raceCar.getStatus(),
                                raceCar.getTop_speed()))
                        .collect(Collectors.toList()),
                driver.getWins(),
                driver.getLoses()));

    }

    public List<Driver> fetchAllDrivers() {

        return driverRepository.findAll().stream().map(driverEntity ->
                new Driver(driverEntity.getFirstName(),
                 driverEntity.getLastName(),
                 driverEntity.getAge(),
                 driverEntity.getNickName(),
                 driverEntity.getCars().stream().map(entity->new RaceCarDTO(entity.getId(), entity.getNickname(), entity.getModel(), entity.getYear(),
                         entity.getOwner(), entity.getStatus(), entity.getTop_speed())).collect(Collectors.toList()),
                 driverEntity.getWins(),
                 driverEntity.getLoses()))
                .collect(Collectors.toList());

    }
}
