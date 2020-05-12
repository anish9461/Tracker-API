package project.truckerapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.truckerapi.entity.Readings;

import java.sql.Timestamp;
import java.util.List;

public interface ReadingsRepository extends CrudRepository<Readings, String> {

    @Query("SELECT r.latitude,r.longitude FROM Readings r where r.vin = :vin and r.created > :timestamp")
    List<Object[]> getVehicleReadings(@Param("vin") String vin, @Param("timestamp") Timestamp timestamp);
}
