package project.truckerapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import project.truckerapi.entity.Readings;
import project.truckerapi.service.ReadingsService;

import java.util.List;

@RestController
@RequestMapping(value = "/readings")
public class ReadingsController {

    @Qualifier("readingsServiceImpl")
    @Autowired
    private ReadingsService readingsService;

    @GetMapping
    public List<Readings> findAllReadings(){
        return readingsService.findAllReadings();
    }

    @PostMapping
    public Readings createReading(@RequestBody Readings reading){
        return readingsService.createReadings(reading);
    }


}
