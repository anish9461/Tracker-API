//package project.truckerapi.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import project.truckerapi.entity.Alerts;
//import project.truckerapi.entity.Readings;
//import project.truckerapi.entity.Tires;
//import project.truckerapi.entity.Vehicle;
//import project.truckerapi.repository.AlertsRepository;
//import project.truckerapi.repository.ReadingsRepository;
//import project.truckerapi.repository.TiresRepository;
//import project.truckerapi.repository.VehicleRepository;
//
//import java.sql.Timestamp;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@ActiveProfiles("integrationtest")
//public class VehicleControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    @Autowired
//    private AlertsRepository alertsRepository;
//
//    @Autowired
//    private ReadingsRepository readingsRepository;
//
//    @Autowired
//    private TiresRepository tiresRepository;
//
//    @Before
//    public void setup(){
//        Vehicle v = new Vehicle();
//        v.setVin("1HGCR2F3XFA027534");
//        v.setMake("HONDA");
//        v.setModel("ACCORD");
//        v.setYear("2015");
//        v.setRedlineRpm(5500);
//        v.setMaxFuelVolume(15);
//        Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
//        v.setLastServiceDate(timestamp4);
//        vehicleRepository.save(v);
//
//        Alerts a = new Alerts();
//        a.setVin("1HGCR2F3XFA027534");
//        a.setRule("Out of range");
//        a.setPriority("MEDIUM");
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        a.setTimestamp(timestamp);
//        alertsRepository.save(a);
//
//
//        Readings r = new Readings();
//        r.setVin("1HGCR2F3XFA027534");
//        r.setCheckEngineLightOn(false);
//        r.setCruiseControlOn(true);
//        r.setEngineCoolantLow(true);
//        r.setEngineHp(300);
//        r.setLatitude(32.22);
//        r.setLongitude(53.75);
//        r.setEngineRpm(5633);
//        r.setSpeed(432);
//        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
//        r.setTimestamp(timestamp1);
//        r.setCreated(timestamp1);
//
//        Tires t = new Tires();
//        t.setRearRight(35);
//        t.setRearLeft(33);
//        t.setFrontRight(32);
//        t.setFrontLeft(31);
//
//        tiresRepository.save(t);
//
//        r.setTires(t);
//        readingsRepository.save(r);
//
//    }
//
//    @After
//    public void cleanup(){
//        vehicleRepository.deleteAll();
//        tiresRepository.deleteAll();
//        readingsRepository.deleteAll();
//        alertsRepository.deleteAll();
//    }
//
//    @Test
//    public void fetchAllVehicles() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("1HGCR2F3XFA027534")));
//    }
//
//    @Test
//    public void createVehicles() throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
//        Vehicle v1 = new Vehicle();
//        v1.setVin("1HGCR2F3XFA027535");
//        v1.setMake("HONDA");
//        v1.setModel("ACCORD");
//        v1.setYear("2015");
//        v1.setRedlineRpm(5500);
//        v1.setMaxFuelVolume(15);
//        Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
//        v1.setLastServiceDate(timestamp4);
//        List<Vehicle> vehicleList = Collections.singletonList(v1);
//        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsBytes(vehicleList)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("1HGCR2F3XFA027535")));
//
//        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
//
//    }
//
//    @Test
//    public void getAllVehicleAlerts() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/vehicles/alerts/1HGCR2F3XFA027534"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("1HGCR2F3XFA027534")));
//    }
//
//
//
//    @Test
//    public void getVehicleLocation() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/vehicles/location/1HGCR2F3XFA027534"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Latitude",Matchers.is("32.22")));
//    }
//
//    @Test
//    public void getVehicleLocation404() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/vehicles/location/1HGCRsdfsaf534"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//
//    }
//}