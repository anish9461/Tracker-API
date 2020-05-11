package project.truckerapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Readings;
import project.truckerapi.service.ReadingsService;

import java.util.List;

@RestController
@RequestMapping(value = "/readings")
@Api(description = "Readings from the Mocker")
public class ReadingsController {

    @Qualifier("readingsServiceImpl")
    @Autowired
    private ReadingsService readingsService;

    @GetMapping
    @ApiOperation(value = "Find All Readings of reported Sensor Data", notes = "Returns a list of all readings of sensor data available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Readings> findAllReadings(){
        return readingsService.findAllReadings();
    }

    @PostMapping
    @ApiOperation(value = "Create the Sensor Data reading ", notes = "Returns the reading created in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Readings createReading(@RequestBody Readings reading){
        return readingsService.createReadings(reading);
    }


}
