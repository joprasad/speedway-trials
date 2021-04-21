package com.speedway.demo.driver;

import com.speedway.demo.model.Driver;
import com.speedway.demo.utils.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DriverController {
    List<Driver> driverList=new ArrayList<>();

    @PostMapping("/driver")
    public ResponseDTO addDriver(@RequestBody Driver driver){
        driverList.add(driver);
        return new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Driver created successfully!");
    }

    @GetMapping("/driver")
    public ResponseDTO getDriver(){
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                driverList);
    }
}
