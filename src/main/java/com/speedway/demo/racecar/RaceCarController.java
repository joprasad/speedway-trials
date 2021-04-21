package com.speedway.demo.racecar;

import com.speedway.demo.utils.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class RaceCarController {

    @GetMapping("racecars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getRacecars() {
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                new ArrayList<RaceCarDTO>());
    }

    @PostMapping("racecar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createRaceCar(@RequestBody RaceCarDTO raceCarDTO) {
        return new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Race car created successfully!");
    }

}
