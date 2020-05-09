package project.truckerapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Alerts {

    @Id
    private String AlertsID;

    private String vin;
    private String Rule;
    private String Priority;
    private String timestamp;


    public Alerts(){
        this.AlertsID = UUID.randomUUID().toString();
    }

    public String getAlertsID() {
        return AlertsID;
    }

    public void setAlertsID(String alertsID) {
        AlertsID = alertsID;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRule() {
        return Rule;
    }

    public void setRule(String rule) {
        Rule = rule;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
