package project.truckerapi.service;

import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;

import java.util.List;

public interface AlertsService {
    List<Alerts> fetchHighAlerts();

    void checkRules(Readings reading);
    void createAlerts(String vin,String rule,String priority);
}
