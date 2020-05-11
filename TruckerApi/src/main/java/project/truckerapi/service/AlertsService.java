package project.truckerapi.service;

import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;

import java.util.List;

public interface AlertsService {
    List<Alerts> fetchHighAlerts();
    List<Alerts> findAllVehicleAlerts(String vin);
    Alerts checkRules(Readings reading);
}
