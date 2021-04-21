package com.speedway.demo.driver;

import com.speedway.demo.model.Driver;
import com.speedway.demo.model.DriverEntity;
import com.speedway.demo.racecar.RaceCarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @InjectMocks
    DriverService driverService;

    @Mock
    DriverRepository driverRepository;

    Driver driver;
    DriverEntity driverEntity;
    RaceCarDTO raceCarDTO;

    @BeforeEach
    public void setup() {
        raceCarDTO = new RaceCarDTO();
        driver = new Driver("Zack", "R", 30, "John", List.of(raceCarDTO), 1, 0);
        driverEntity = new DriverEntity("Zack", "R", 30, "John", List.of(raceCarDTO), 1, 0);

    }

    @Test
    public void saveDriverTest() {

        driverService.saveDriver(driver);

        verify(driverRepository).save(driverEntity);

    }

    @Test
    public void fetchAllDrivers() {

        when(driverRepository.findAll()).thenReturn(List.of(driverEntity));

        List<Driver> driverList = driverService.fetchAllDrivers();

        assertEquals(driverList, List.of(driver));

    }

}
