package project.truckerapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import project.truckerapi.repository.TiresRepository;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
public class Readings {

    @Id
    private String ReadingsID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tires_tiresid")
    private Tires tires;

    private String vin;
    private double fuelVolume;
    private double speed;
    private double engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private double engineRpm;
    private double latitude;
    private double longitude;
    private String timestamp;

    public Readings() {
        this.ReadingsID = UUID.randomUUID().toString();
    }

    @JsonProperty("tires")
    private void mapTires(Map<String, Integer> tires) {
        System.out.println("tires " + tires.get("frontLeft"));
        this.tires = new Tires();
        this.tires.setFrontLeft(tires.get("frontLeft"));
        this.tires.setFrontRight(tires.get("frontRight"));
        this.tires.setRearLeft(tires.get("rearLeft"));
        this.tires.setRearRight(tires.get("rearRight"));
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getReadingsID() {
        return ReadingsID;
    }

    public void setReadingsID(String readingsID) {
        ReadingsID = readingsID;
    }

    public double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(double engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public double getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(double engineRpm) {
        this.engineRpm = engineRpm;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
