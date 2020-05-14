package project.truckerapi.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.repository.AlertsRepository;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.TiresRepository;
import project.truckerapi.repository.VehicleRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ReadingsServiceImplTest {

    @TestConfiguration
    static class ReadingsServiceImplTestConfiguration{

        @Bean
        public ReadingsService getReadingsService(){
            return new ReadingsServiceImpl();
        }

        @Bean
        public  AlertsService getAlertService(){
            return new AlertsServiceImpl();
        }

        @Bean
        public VehicleService getVehicleService(){
            return new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReadingsService readingsService;

    @Autowired
    private AlertsService alertsService;

    @MockBean
    private AlertsRepository alertsRepository;

    @MockBean
    private ReadingsRepository readingsRepository;

    @MockBean
    private TiresRepository tiresRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    private List<Readings> readingsList;

    @Before
    public void setUp() throws Exception {
        Readings r = new Readings();
        r.setVin("1HGCR2F3XFA027534");
        r.setCheckEngineLightOn(false);
        r.setCruiseControlOn(true);
        r.setEngineCoolantLow(true);
        r.setEngineHp(300);
        r.setLatitude(32.22);
        r.setLongitude(53.75);
        r.setEngineRpm(5633);
        r.setSpeed(432);
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        r.setTimestamp(timestamp1);
        r.setCreated(timestamp1);

        Tires t = new Tires();
        t.setRearRight(35);
        t.setRearLeft(33);
        t.setFrontRight(32);
        t.setFrontLeft(31);

        r.setTires(t);

        readingsList = Collections.singletonList(r);

        Mockito.when(readingsRepository.findAll()).thenReturn(readingsList);

        Mockito.when(tiresRepository.save(t)).thenReturn(t);
        Mockito.when(readingsRepository.save(r)).thenReturn(r);

    }

@After
public void cleanup(){

}

    @Test
    public void findAllReadings() throws Exception{
        List<Readings> result = readingsService.findAllReadings();
        Assert.assertEquals("List of Readings match",readingsList,result);
    }

    @Test
    public void createReadings() throws Exception{
        Readings result = readingsService.createReadings(readingsList.get(0));
        Assert.assertEquals("Reading is created",readingsList.get(0),result);
    }
}