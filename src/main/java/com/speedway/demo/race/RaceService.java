package com.speedway.demo.race;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<RaceDTO> getRaces() {
        return raceRepository.findAll().stream()
                .map(entity -> createRaceDto(entity))
                .collect(Collectors.toList());
    }

    public void createRace(RaceDTO raceDto) {
        this.raceRepository.save(createRaceEntity(raceDto));
    }

    private RaceDTO createRaceDto(RaceEntity entity) {
        return new RaceDTO(entity.getName(), entity.getCategory(), entity.getDate(),
                entity.getBestTime(), entity.getWinner(), entity.getParticipants());
    }

    private RaceEntity createRaceEntity(RaceDTO raceDTO) {
        return new RaceEntity(raceDTO.getName(), raceDTO.getCategory(), raceDTO.getDate(),
                raceDTO.getBestTime(), raceDTO.getWinner(), raceDTO.getParticipants());
    }
}
