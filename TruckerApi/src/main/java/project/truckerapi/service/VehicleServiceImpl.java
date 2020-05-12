package project.truckerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.exception.BadRequestException;
import project.truckerapi.repository.AlertsRepository;
import project.truckerapi.repository.ReadingsRepository;
import project.truckerapi.repository.VehicleRepository;

import java.sql.Timestamp;
import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private AlertsRepository alertsRepository;

//    @Autowired
//    private LocationRepository locationRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAllVehicles(){
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Transactional
    public List<Vehicle> createVehicle(List<Vehicle> vehicles){

        for(Vehicle vehicle : vehicles) {
            System.out.println("Vehicle "+vehicle.toString());

            vehicleRepository.save(vehicle);
        }
         return vehicles;

    }

    @Transactional(readOnly = true)
    public Vehicle getVehicleDetails(String vin) {
        Optional<Vehicle> existing = vehicleRepository.findById(vin);
        if(existing.isPresent()){
            return existing.get();
        }
        else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Alerts> findAllVehicleAlerts(String vin) {
        return alertsRepository.findAllByVin(vin);
    }

    @Transactional(readOnly = true)
    public List<Map<String,String>> getVehicleLocation(String vin) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -1);
        Timestamp t = new Timestamp(c.getTimeInMillis());
        System.out.println(t);

        List<Object[]> result = readingsRepository.getVehicleReadings(vin, t);
        Map<String, String> map = null;
        if (result != null && !result.isEmpty()) {
            List<Map<String, String>> output = new ArrayList();
            for (Object[] object : result) {
                map = new HashMap<String, String>();
                map.put("Latitude", object[0].toString());
                map.put("Longitude", object[1].toString());
                output.add(map);
            }
            return output;
        }
        else{
            return null;
        }
    }

}
