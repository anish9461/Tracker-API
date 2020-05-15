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
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.repository.AlertsRepository;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.VehicleRepository;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

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

    private List<Vehicle> vehicleList;

    private List<Alerts> alertsList;

    private List<Map<String,String>> output;

    @Before
    public void setup(){
        Vehicle v = new Vehicle();
        v.setVin("1HGCR2F3XFA027534");
        v.setMake("HONDA");
        v.setModel("ACCORD");
        v.setYear("2015");
        v.setRedlineRpm(5500);
        v.setMaxFuelVolume(15);
        Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
        v.setLastServiceDate(timestamp4);

        vehicleList = Collections.singletonList(v);

        Alerts a = new Alerts();
        a.setVin("1HGCR2F3XFA027534");
        a.setRule("Out of range");
        a.setPriority("MEDIUM");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        a.setTimestamp(timestamp);

        alertsList = Collections.singletonList(a);

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

        Map<String, String> map = null;
        output = new ArrayList();
        map = new HashMap<String, String>();
        map.put("Latitude", String.valueOf(r.getLatitude()));
        map.put("Longitude", String.valueOf(r.getLongitude()));
        output.add(map);

        //when
        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicleList);
        //return

        Mockito.when(vehicleRepository.findById("1HGCR2F3XFA027534")).thenReturn(Optional.of(v));

        Mockito.when(vehicleRepository.save(v)).thenReturn(v);

        Mockito.when(alertsRepository.findAllByVin("1HGCR2F3XFA027534")).thenReturn(alertsList);

        List<Object[]> objectlist = new ArrayList<Object[]>();
        Object obj[] = new Object[2];
        obj[0] = 32.22;
        obj[1] = 53.75;
        objectlist.add(obj);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -30);
        Timestamp timestamp3 = new Timestamp(c.getTimeInMillis());
        Mockito.when(readingsRepository.getVehicleReadings("1HGCR2F3XFA027534",timestamp3)).thenReturn(objectlist);
    }

@After()
public void cleanup(){

}

    @Test
    public void findAllVehicles() throws Exception{
List<Vehicle> result = vehicleService.findAllVehicles();
        Assert.assertEquals("Vehicle list should match", vehicleList, result);
    }

    @Test
    public void createVehicle() throws Exception{
        List<Vehicle> result = vehicleService.createVehicle(vehicleList);
        Assert.assertEquals("Vehicals are created successfully",vehicleList,result);
    }

    @Test
    public void getVehicleDetails() throws Exception{
        Vehicle result = vehicleService.getVehicleDetails("1HGCR2F3XFA027534");
        Assert.assertEquals("Vehicle details should match",vehicleList.get(0),result);
    }



    @Test
    public void findAllVehicleAlerts() throws Exception{
        List<Alerts> result = vehicleService.findAllVehicleAlerts("1HGCR2F3XFA027534");
        Assert.assertEquals("List of Alerts should match",alertsList,result);
    }

    @Test
    public void getVehicleLocation() throws Exception{
        List<Map<String,String>> result = vehicleService.getVehicleLocation("1HGCR2F3XFA027534");
        Assert.assertEquals("Location Test match",output,result);
    }

//    @Test
//    public void getVehicleLocationNotFound() throws Exception{
//        List<Map<String,String>> result = vehicleService.getVehicleLocation("asfd");
//        Assert.assertEquals("Location Test match",output,result);
//    }
}