package project.truckerapi.entity;

import com.mysql.cj.protocol.ColumnDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Alerts {

    @Id
    private String AlertsID;

    private String vin;
    private String Rule;
    private String Priority;
    //@Column(columnDefinition = "TIMESTAMP")
    private Timestamp timestamp;


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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
