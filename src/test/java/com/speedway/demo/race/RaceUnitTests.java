package com.speedway.demo.race;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RaceUnitTests {

    @Mock
    RaceRepository raceRepository;

    @InjectMocks
    RaceService raceService;

    @Test
    public void getRacesTest() {
        when(raceRepository.findAll()).thenReturn(List.of(
                new RaceEntity("Grand Prix III","stock car", LocalDate.of(2020,6,3),
                        "03:36:78",12L, new ArrayList<Integer>(Arrays.asList(23,45,12)))
        ));
        var result = raceService.getRaces();
        assertThat(result).isEqualTo(List.of(
                new RaceDTO("Grand Prix III","stock car", LocalDate.of(2020,6,3),
                        "03:36:78",12L, new ArrayList<Integer>(Arrays.asList(23,45,12)))
        ));
    }

    @Test
    public void createRaceTest() {
        var raceDto = new RaceDTO("Grand Prix III","stock car", LocalDate.of(2020,6,3),
                "03:36:78",12L, new ArrayList<Integer>(Arrays.asList(23,45,12)));
        var entity = new RaceEntity("Grand Prix III","stock car", LocalDate.of(2020,6,3),
                "03:36:78",12L, new ArrayList<Integer>(Arrays.asList(23,45,12)));
        raceService.createRace(raceDto);
        verify(raceRepository, times(1)).save(entity);
    }

}
