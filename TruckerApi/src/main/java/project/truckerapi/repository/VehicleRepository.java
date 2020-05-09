package project.truckerapi.repository;

import org.springframework.data.repository.CrudRepository;
import project.truckerapi.entity.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

    Optional<Vehicle> findByVin(String vin);
}
