package project.truckerapi.service;


import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> findAllVehicles();

    List<Vehicle> createVehicle(List<Vehicle> vehicles);

    Vehicle getVehicleDetails(String vin);

    List<Alerts> findAllVehicleAlerts(String vin);

    List<Map<String,String>> getVehicleLocation(String vin);

}
