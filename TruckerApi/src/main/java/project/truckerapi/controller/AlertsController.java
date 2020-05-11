package project.truckerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.service.AlertsService;

import java.util.List;

@RestController
public class AlertsController {

    @Autowired
    private AlertsService alertsService;

    @GetMapping(value = "/alerts/high")
    public List<Alerts> fetchHighAlerts(){
        return alertsService.fetchHighAlerts();
    }

    @GetMapping(value = "/alerts/{id}")
    public List<Alerts> getAllVehicleAlerts(@PathVariable("id") String vin){
        return alertsService.findAllVehicleAlerts(vin);
    }

    @PostMapping(value = "/readings")
    public Alerts checkRules(@RequestBody Readings reading){
        return alertsService.checkRules(reading);
    }
}
