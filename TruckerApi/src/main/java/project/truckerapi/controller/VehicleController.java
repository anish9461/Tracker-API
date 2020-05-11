package project.truckerapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.service.VehicleService;

import java.util.List;

@CrossOrigin(origins = "http://mocker.egen.academy")
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> fetchAllVehicles(){
        return vehicleService.findAllVehicles();
    }

    @PutMapping
    public List<Vehicle> createVehicles(@RequestBody List<Vehicle> vehicles){
        return vehicleService.createVehicle(vehicles);
    }



}
