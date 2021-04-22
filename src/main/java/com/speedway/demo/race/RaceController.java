package com.speedway.demo.race;

import com.speedway.demo.racecar.RaceCarDTO;
import com.speedway.demo.utils.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class RaceController {
    ArrayList<RaceDTO> repo = new ArrayList<>();

    @GetMapping("races")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getRaces() {
        return new ResponseDTO(HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
               repo);
    }

    @PostMapping("race")
    public ResponseEntity<ResponseDTO> createRace(@RequestBody RaceDTO raceDTO) {
        repo.add(raceDTO);
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.CREATED.getReasonPhrase(),
                HttpStatus.CREATED.value(),
                "Race created successfully!"), HttpStatus.CREATED);

    }
}
