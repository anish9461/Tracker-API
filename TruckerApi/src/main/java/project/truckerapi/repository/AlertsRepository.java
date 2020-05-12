package project.truckerapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.truckerapi.entity.Alerts;

import java.sql.Timestamp;
import java.util.List;

public interface AlertsRepository extends CrudRepository<Alerts, String> {
    List<Alerts> findAllByVin(String vin);

    @Query("From Alerts A WHERE A.Priority=:priority AND A.timestamp > :timestamp")
    List<Alerts> getHighAlerts(@Param("priority")String priority,@Param("timestamp")Timestamp timestamp);
}
