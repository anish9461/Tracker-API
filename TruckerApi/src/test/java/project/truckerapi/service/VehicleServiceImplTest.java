package project.truckerapi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import project.truckerapi.repository.AlertsRepository;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.VehicleRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {

    @TestConfiguration
    static class VehicleServiceImplTestConfiguration{

        @Bean
        public VehicleService getService(){
            return new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private ReadingsRepository readingsRepository;

    @MockBean
    private AlertsRepository alertsRepository;

    @Test
    public void findAllVehicles() throws Exception{

    }

    @Test
    public void createVehicle() throws Exception{
    }

    @Test
    public void getVehicleDetails() throws Exception{
    }

    @Test
    public void findAllVehicleAlerts() throws Exception{
    }

    @Test
    public void getVehicleLocation() throws Exception{
    }
}