package project.truckerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.truckerapi.entity.Tires;
import project.truckerapi.service.TiresService;

@RestController
@RequestMapping(value = "/tires")
public class TiresController {

    @Autowired
    private TiresService tiresService;

    @PostMapping
    public Tires createTires(@RequestBody Tires tires){
        return tiresService.createTires(tires);
    }
}
