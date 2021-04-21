package com.speedway.demo.racecar;

import com.speedway.demo.utils.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class RaceCarController {
    private final RaceCarService raceCarService;

    public RaceCarController(RaceCarService raceCarService) {
        this.raceCarService = raceCarService;
    }

    @GetMapping("racecars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getRacecars() {
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                this.raceCarService.getRaceCars());
    }

    @PostMapping("racecar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createRaceCar(@RequestBody RaceCarDTO raceCarDTO) {
        this.raceCarService.createRaceCar(raceCarDTO);
        return new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Race car created successfully!");
    }

}
