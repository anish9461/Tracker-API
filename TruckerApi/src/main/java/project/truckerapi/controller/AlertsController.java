package project.truckerapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Alerts;
import project.truckerapi.service.AlertsService;

import java.util.List;

@RestController
@RequestMapping(value = "/alerts")
@Api(description = "Alerts for the Sensors")
public class AlertsController {

    @Autowired
    private AlertsService alertsService;

    @GetMapping(value = "/high")
    @ApiOperation(value = "Get the HIGH alerts", notes = "Returns a list of all HIGH alerts available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alerts> fetchHighAlerts(){
        return alertsService.fetchHighAlerts();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get vehicle's all Alerts", notes = "Returns a list of all alerts for the vehicle available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alerts> getAllVehicleAlerts(@PathVariable("id") String vin){
        return alertsService.findAllVehicleAlerts(vin);
    }

}
