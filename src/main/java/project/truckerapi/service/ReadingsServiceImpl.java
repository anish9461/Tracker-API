package project.truckerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.exception.VehicleNotFoundException;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.TiresRepository;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingsServiceImpl implements ReadingsService{

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private TiresRepository tiresRepository;

//    @Qualifier("alertsServiceImpl")
    @Autowired
    private AlertsService alertsService;

   // @Qualifier("vehicleServiceImpl")
    @Autowired
    private VehicleService vehicleService;

    @Transactional(readOnly = true)
    public List<Readings> findAllReadings(){
        return (List<Readings>) readingsRepository.findAll();
    }

    @Transactional
    public Readings createReadings(Readings reading){
        Optional<Vehicle> v = Optional.ofNullable(vehicleService.getVehicleDetails(reading.getVin()));
        if(!v.isPresent()){
            throw new VehicleNotFoundException("Vehicle with id "+reading.getVin()+" does not exist");
        }
        Tires tires = reading.getTires();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        reading.setCreated(timestamp);
        alertsService.checkRules(reading);
        tiresRepository.save(tires);
        return readingsRepository.save(reading);
    }
}
