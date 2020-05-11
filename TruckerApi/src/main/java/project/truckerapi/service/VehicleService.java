package project.truckerapi.service;

import netscape.javascript.JSObject;
import project.truckerapi.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAllVehicles();

    List<Vehicle> createVehicle(List<Vehicle> vehicles);

    Vehicle getVehicleDetails(String vin);

    List<JSObject> getVehicleLocation(String vin);

}
