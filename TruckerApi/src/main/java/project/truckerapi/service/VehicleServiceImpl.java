package project.truckerapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.exception.BadRequestException;
import project.truckerapi.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

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


//    @Transactional(readOnly = true)
//    public List<JSObject> getVehicleLocation(String vin) {
//        return null;
//    }
}
