package project.truckerapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.TiresRepository;
import project.truckerapi.repository.VehicleRepository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


class tempReadings{

    public String vin;
    public double fuelVolume;
    public double speed;
    public double engineHp;
    public boolean checkEngineLightOn;
    public boolean engineCoolantLow;
    public boolean cruiseControlOn;
    public double engineRpm;
    public double latitude;
    public double longitude;
    public String timestamp;

    public Map<String,Integer> tires;

}

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class ReadingsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private TiresRepository tiresRepository;

    @Before
    public void setUp() throws Exception {
        Vehicle v = new Vehicle();
        v.setVin("1HGCR2F3XFA027534");
        v.setMake("HONDA");
        v.setModel("ACCORD");
        v.setYear("2015");
        v.setRedlineRpm(5500);
        v.setMaxFuelVolume(15);
        Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
        v.setLastServiceDate(timestamp4);

        vehicleRepository.save(v);

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
        tiresRepository.save(t);
        r.setTires(t);
        readingsRepository.save(r);
    }

    @After
    public void tearDown() throws Exception {
        vehicleRepository.deleteAll();
        readingsRepository.deleteAll();
        tiresRepository.deleteAll();
    }

    @Test
    public void findAllReadings() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/readings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("1HGCR2F3XFA027534")));
    }

    @Test
    public void createReading() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        tempReadings r1 = new tempReadings();
        r1.vin = "1HGCR2F3XFA027534";
        r1.checkEngineLightOn = false;
        r1.cruiseControlOn = true;
        r1.engineCoolantLow = true;
        r1.engineHp = 3242;
        r1.engineRpm = 4532;
        r1.fuelVolume = 3222;
        r1.latitude = 32.4;
        r1.longitude = 43.42;

        r1.timestamp = "2017-05-25T17:31:25.268Z";
        r1.speed = 3453;
        r1.tires = new HashMap<>();
        r1.tires.put("frontRight",32);
        r1.tires.put("frontLeft",32);
        r1.tires.put("rearRight",32);
        r1.tires.put("rearLeft",32);



        mvc.perform(MockMvcRequestBuilders.post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(r1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vin", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vin",Matchers.is("1HGCR2F3XFA027534")));

        mvc.perform(MockMvcRequestBuilders.get("/readings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }
}