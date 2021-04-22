package com.speedway.demo.race;

import com.speedway.demo.utils.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class RaceController {

    RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("races")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getRaces() {
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
               raceService.getRaces());
    }

    @PostMapping("race")
    public ResponseEntity<ResponseDTO> createRace(@RequestBody RaceDTO raceDTO) {
        raceService.createRace(raceDTO);
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Race created successfully!"), HttpStatus.CREATED);

    }
}
