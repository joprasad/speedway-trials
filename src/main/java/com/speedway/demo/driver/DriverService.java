package com.speedway.demo.driver;


import com.speedway.demo.model.Driver;
import com.speedway.demo.model.DriverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
