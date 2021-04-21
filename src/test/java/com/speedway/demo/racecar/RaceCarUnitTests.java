package com.speedway.demo.racecar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RaceCarUnitTests {

    @Mock
    RaceCarRepository raceCarRepository;

    @InjectMocks
    RaceCarService raceCarService;

    @Test
    public void getRaceCarsTest() {
        when(raceCarRepository.findAll()).thenReturn(List.of(
                new RaceCarEntity(1L,"The Condor", "Corvette", 2019, 27,
                        "AVAILABLE", 189)
        ));
        var result = raceCarService.getRaceCars();
        assertThat(result).isEqualTo(List.of(
                new RaceCarDTO(1L,"The Condor", "Corvette", 2019, 27,
                        "AVAILABLE", 189)
        ));
    }

    @Test
    public void createRaceCarTest() {
        var raceCarDto = new RaceCarDTO("The Condor", "Corvette", 2019, 27,
                "AVAILABLE", 189);
        var entity = new RaceCarEntity("The Condor", "Corvette", 2019, 27,
                "AVAILABLE", 189);
        raceCarService.createRaceCar(raceCarDto);
        verify(raceCarRepository, times(1)).save(entity);
    }

}
