package com.speedway.demo.driver;


import com.speedway.demo.model.Driver;
import com.speedway.demo.model.DriverEntity;
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
                driver.getCars(),
                driver.getWins(),
                driver.getLoses()));

    }

    public List<Driver> fetchAllDrivers() {

        return driverRepository.findAll().stream().map(driverEntity ->
                new Driver(driverEntity.getFirstName(),
                 driverEntity.getLastName(),
                 driverEntity.getAge(),
                 driverEntity.getNickName(),
                 driverEntity.getCars(),
                 driverEntity.getWins(),
                 driverEntity.getLoses()))
                .collect(Collectors.toList());

    }
}
