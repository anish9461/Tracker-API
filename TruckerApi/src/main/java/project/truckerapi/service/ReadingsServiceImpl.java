package project.truckerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.TiresRepository;

import java.util.List;

@Service
public class ReadingsServiceImpl implements ReadingsService{

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private TiresRepository tiresRepository;

    @Transactional(readOnly = true)
    public List<Readings> findAllReadings(){
        return (List<Readings>) readingsRepository.findAll();
    }

    @Transactional
    public Readings createReadings(Readings reading){
        //check if vin is present in database
        Tires tires = reading.getTires();
        tiresRepository.save(tires);
        return readingsRepository.save(reading);
    }
}
