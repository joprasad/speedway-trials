package com.speedway.demo;

import com.speedway.demo.model.Driver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DriverController {
    List<Driver> driverList=new ArrayList<>();

    @PostMapping("/driver")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver(@RequestBody Driver driver){
        driverList.add(driver);

    }

    @GetMapping("/driver")
    public List<Driver> getDriver(){
        return driverList;

    }
}
