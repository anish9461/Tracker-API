package project.truckerapi.repository;

import org.springframework.data.repository.CrudRepository;
import project.truckerapi.entity.Alerts;

import java.util.List;

public interface AlertsRepository extends CrudRepository<Alerts, String> {
    List<Alerts> findAllByVin(String vin);
}
