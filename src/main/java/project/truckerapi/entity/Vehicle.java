package project.truckerapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Vehicle {

    @Id
    private String vin;

    private String model;
    private String make;
    private String year;
    private double redlineRpm;
    private double maxFuelVolume;
    private Timestamp lastServiceDate;



    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getRedlineRpm() {
        return redlineRpm;
    }

    public void setRedlineRpm(double redlineRpm) {
        this.redlineRpm = redlineRpm;
    }

    public double getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(double maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public Timestamp getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Timestamp lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }


}
