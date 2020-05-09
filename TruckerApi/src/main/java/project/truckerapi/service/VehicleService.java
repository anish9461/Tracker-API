package project.truckerapi.service;

import project.truckerapi.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAllVehicles();

    List<Vehicle> createVehicle(List<Vehicle> vehicles);

}
