package com.speedway.demo.racecar;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceCarService {

    private final RaceCarRepository raceCarRepository;

    public RaceCarService(RaceCarRepository raceCarRepository) {
        this.raceCarRepository = raceCarRepository;
    }

    public List<RaceCarDTO> getRaceCars() {
        return raceCarRepository.findAll().stream()
                .map(entity -> createRaceCarDto(entity))
                .collect(Collectors.toList());
    }

    public void createRaceCar(RaceCarDTO raceCarDto) {
        this.raceCarRepository.save(createRaceCarEntity(raceCarDto));
    }

    private RaceCarDTO createRaceCarDto(RaceCarEntity entity) {
        return new RaceCarDTO(entity.getNickname(), entity.getModel(), entity.getYear(),
                entity.getOwner(), entity.getStatus(), entity.getTop_speed());
    }

    private RaceCarEntity createRaceCarEntity(RaceCarDTO raceCarDTO) {
        return new RaceCarEntity(raceCarDTO.getNickname(), raceCarDTO.getModel(), raceCarDTO.getYear(),
                raceCarDTO.getOwner(), raceCarDTO.getStatus(), raceCarDTO.getTop_speed());
    }

}
