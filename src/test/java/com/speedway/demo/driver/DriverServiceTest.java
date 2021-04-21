package com.speedway.demo.driver;

import com.speedway.demo.model.Driver;
import com.speedway.demo.model.DriverEntity;
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

    @Test
    public void saveDriverTest() {

        Driver driver = new Driver("Zack", "R", 30, "John", "", 1, 0);

        driverService.saveDriver(driver);

        verify(driverRepository).save(new DriverEntity("Zack", "R", 30, "John", "", 1, 0));

    }

    @Test
    public void fetchAllDrivers() {
        DriverEntity driverEntity = new DriverEntity("Zack", "R", 30, "John", "", 1, 0);
        Driver driver = new Driver("Zack", "R", 30, "John", "", 1, 0);

        when(driverRepository.findAll()).thenReturn(List.of(driverEntity));

        List<Driver> driverList = driverService.fetchAllDrivers();


        assertEquals(driverList, List.of(driver));

    }

}
