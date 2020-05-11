package project.truckerapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.service.VehicleService;

import java.util.List;

@CrossOrigin(origins = "http://mocker.egen.academy")
@RestController
@RequestMapping(value = "/vehicles")
@Api(description = "Vehicle related REST API calls")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping
    @ApiOperation(value = "Get All the Vehicles", notes = "Returns a list of all vehicles available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Vehicle> fetchAllVehicles(){
        return vehicleService.findAllVehicles();
    }

    @PutMapping
    @ApiOperation(value = "Creates or updates the vehicle details", notes = "Returns a list of all vehicles created or updated in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Vehicle> createVehicles(@RequestBody List<Vehicle> vehicles){
        return vehicleService.createVehicle(vehicles);
    }

    @GetMapping(value = "/alerts/{id}")
    @ApiOperation(value = "Get vehicle's all Alerts", notes = "Returns a list of all alerts for the vehicle available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alerts> getAllVehicleAlerts(@PathVariable("id") String vin){
        return vehicleService.findAllVehicleAlerts(vin);
    }

}
