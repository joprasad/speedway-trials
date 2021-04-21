package com.speedway.demo.racecar;

import com.speedway.demo.utils.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RaceCarController {
    List<RaceCarDTO> raceCars = new ArrayList<>();

    @GetMapping("racecars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getRacecars() {
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                raceCars);
    }

    @PostMapping("racecar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createRaceCar(@RequestBody RaceCarDTO raceCarDTO) {
        raceCars.add(raceCarDTO);
        return new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Race car created successfully!");
    }

}
