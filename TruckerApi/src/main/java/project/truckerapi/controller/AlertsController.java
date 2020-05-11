package project.truckerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Alerts;
import project.truckerapi.service.AlertsService;

import java.util.List;

@RestController
@RequestMapping(value = "/alerts")
public class AlertsController {

    @Autowired
    private AlertsService alertsService;

    @GetMapping(value = "/high")
    public List<Alerts> fetchHighAlerts(){
        return alertsService.fetchHighAlerts();
    }

    @GetMapping(value = "/{id}")
    public List<Alerts> getAllVehicleAlerts(@PathVariable("id") String vin){
        return alertsService.findAllVehicleAlerts(vin);
    }

}
