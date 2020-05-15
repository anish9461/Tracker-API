package project.truckerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.exception.BadRequestException;
import project.truckerapi.exception.VehicleNotFoundException;
import project.truckerapi.repository.AlertsRepository;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.VehicleRepository;

import java.sql.Timestamp;
import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ReadingsRepository readingsRepository;

    @Autowired
    AlertsRepository alertsRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAllVehicles(){
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Transactional
    public List<Vehicle> createVehicle(List<Vehicle> vehicles){
        vehicles.forEach(vehicle -> vehicleRepository.save(vehicle));
        return vehicles;
    }

    @Transactional(readOnly = true)
    public Vehicle getVehicleDetails(String vin) {
        Optional<Vehicle> existing = vehicleRepository.findById(vin);
        return existing.orElseThrow(() -> new VehicleNotFoundException(("Vehicle with id "+vin+" does not exist")));

    }

    @Transactional(readOnly = true)
    public List<Alerts> findAllVehicleAlerts(String vin) {

        Optional<List<Alerts>> alerts = Optional.ofNullable(alertsRepository.findAllByVin(vin));
        return alerts.orElseThrow(() -> new VehicleNotFoundException(("Vehicle with id "+vin+" does not exist")));
    }

    @Transactional(readOnly = true)
    public List<Map<String,String>> getVehicleLocation(String vin) {
        Optional<Vehicle> existing = vehicleRepository.findById(vin);
        if(!existing.isPresent()){
            throw new VehicleNotFoundException(("Vehicle with id "+vin+" does not exist"));
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -30);
        Timestamp t = new Timestamp(c.getTimeInMillis());
        List<Object[]> result = readingsRepository.getVehicleReadings(vin, t);
        Map<String, String> map = null;
        List<Map<String, String>> output = new ArrayList();
        if (result != null && !result.isEmpty()) {
            for (Object[] object : result) {
                map = new HashMap<String, String>();
                map.put("Latitude", object[0].toString());
                map.put("Longitude", object[1].toString());
                output.add(map);
            }
        }
        return output;

    }
}
