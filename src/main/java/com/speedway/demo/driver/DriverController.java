package com.speedway.demo.driver;

import com.speedway.demo.model.Driver;
import com.speedway.demo.utils.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/api/v1/driver")
    public ResponseDTO addDriver(@RequestBody Driver driver){
        driverService.saveDriver(driver);
        return new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Driver created successfully!");
    }

    @GetMapping("/api/v1/driver")
    public ResponseDTO getDriver(){
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                driverService.fetchAllDrivers());
    }
}
