package project.truckerapi.repository;

import org.springframework.data.repository.CrudRepository;
import project.truckerapi.entity.Alerts;

public interface AlertsRepository extends CrudRepository<Alerts, String> {
}
