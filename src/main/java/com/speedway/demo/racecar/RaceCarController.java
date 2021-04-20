package com.speedway.demo.racecar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class RaceCarController {

    @GetMapping("racecars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getRacecars(){
        return new ResponseDTO("OK", 200, new ArrayList<Object>());
    }

}
