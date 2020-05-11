package project.truckerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.repository.AlertsRepository;

import java.util.List;

@Service
public class AlertsServiceImpl implements AlertsService{

    @Autowired
    private AlertsRepository alertsRepository;

    @Transactional(readOnly = true)
    public List<Alerts> fetchHighAlerts() {
        return null;
    }

    @Transactional(readOnly = true)
    public List<Alerts> findAllVehicleAlerts(String vin) {
        return null;
    }

    @Transactional
    public Alerts checkRules(Readings reading) {
        return null;
    }
}
