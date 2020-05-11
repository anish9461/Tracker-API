package project.truckerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.repository.AlertsRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class AlertsServiceImpl implements AlertsService{

    @Autowired
    private AlertsRepository alertsRepository;

    @Autowired
    private VehicleService vehicleService;

    @Transactional(readOnly = true)
    public List<Alerts> fetchHighAlerts() {
        return null;
    }



    @Transactional
    public void createAlerts(String vin,String rule,String priority){
        Alerts alert = new Alerts();
        alert.setPriority(priority);
        alert.setRule(rule);
        alert.setVin(vin);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        alert.setTimestamp(timestamp);
        alertsRepository.save(alert);
    }


    @Transactional
    public void checkRules(Readings reading) {
        //Fetch the vehicle data using find one function and check the four cases
        String vehicleID = reading.getVin();
        Tires tires = reading.getTires();
        int leftFTire = tires.getFrontLeft();
        int leftRTire = tires.getRearLeft();
        int rightFTire = tires.getFrontRight();
        int rightRTire = tires.getRearRight();
        String rule = "";
        if(vehicleService.getVehicleDetails(vehicleID) != null){
            System.out.println("Vehicle present");
            Vehicle vehicle = vehicleService.getVehicleDetails(vehicleID);
            if(reading.getEngineRpm() > vehicle.getRedlineRpm()){
                rule = "Vehicle Engine RPM is : "+reading.getEngineRpm()+" greater than Readline RPM of : "+vehicle.getRedlineRpm();
                createAlerts(vehicleID,rule,"HIGH");
            }
            if(reading.getFuelVolume() < (0.10 * vehicle.getMaxFuelVolume())){
                rule = "Vehicle Fuel volume is : "+reading.getFuelVolume()+" less than 10% of the maximum fuel volume of : "+vehicle.getMaxFuelVolume();
                createAlerts(vehicleID,rule,"MEDIUM");
            }
            if(leftFTire > 36 || leftFTire < 32){
                rule = "Front Left tire pressure is : "+leftFTire+"psi";
                createAlerts(vehicleID,rule,"LOW");
            }
            if(leftRTire > 36 || leftRTire < 32){
                rule = "Rear Left tire pressure is : "+leftRTire+"psi";
                createAlerts(vehicleID,rule,"LOW");
            }
            if(rightFTire > 36 || rightFTire < 32){
                rule = "Front Right tire pressure is : "+rightFTire+"psi";
                createAlerts(vehicleID,rule,"LOW");
            }
            if(rightRTire > 36 || rightRTire < 32){
                rule = "Rear Right tire pressure is : "+rightRTire+"psi";
                createAlerts(vehicleID,rule,"LOW");
            }
            if(reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()){
                if(reading.isEngineCoolantLow()){
                    rule = "Engine Coolant is Low";
                    createAlerts(vehicleID,rule,"LOW");
                }
                if(reading.isCheckEngineLightOn()){
                    rule = "Engine Light is On";
                    createAlerts(vehicleID,rule,"LOW");
                }
            }
        }

    }
}
