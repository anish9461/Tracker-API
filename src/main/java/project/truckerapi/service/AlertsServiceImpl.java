package project.truckerapi.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.costandusagereport.model.AWSRegion;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Alerts;
import project.truckerapi.entity.Readings;
import project.truckerapi.entity.Tires;
import project.truckerapi.entity.Vehicle;
import project.truckerapi.repository.AlertsRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


@Service
public class AlertsServiceImpl implements AlertsService{

    @Autowired
    private Environment env;

    @Autowired
    private AlertsRepository alertsRepository;

   // @Qualifier("vehicleServiceImpl")
    @Autowired
    private VehicleService vehicleService;

    private String email_from = "anishnkr@anishnesarkar.com";
    private String email_to = "anish9461@gmail.com";
    private String subject = "HIGH Alert Notification for Trucker API";
    private String email_body = "";
    private Regions AWS_REGION = Regions.US_EAST_1;

    @Transactional(readOnly = true)
    public List<Alerts> fetchHighAlerts() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -2);
        Timestamp t = new Timestamp(c.getTimeInMillis());
        return alertsRepository.getHighAlerts("HIGH",t);
    }

    public void SendEmail(){
        BasicAWSCredentials b = new BasicAWSCredentials(env.getProperty("aws.AccessKey"), env.getProperty("aws.SecretKey"));
        AWSCredentials credentials = null;
        try {
            credentials = b;
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Wrong credentials ", e
            );
        }
        AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
        Region region = Region.getRegion(AWS_REGION);
        client.setRegion(region);
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(email_to))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withText(new Content()
                                        .withCharset("UTF-8").withData(email_body)))
                        .withSubject(new Content()
                                .withCharset("UTF-8").withData(subject)))
                .withSource(email_from);
        client.sendEmail(request);
        System.out.println("Email sent");
    }


    @Transactional
    public void createAlerts(String vin,String rule,String priority){
        Alerts alert = new Alerts();
        alert.setPriority(priority);
        alert.setRule(rule);
        alert.setVin(vin);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        alert.setTimestamp(timestamp);
        //if alert priority is high send email
        if(priority == "HIGH") {
            email_body = "The rule " + alert.getRule() + " is violated";
           // SendEmail();
        }
        alertsRepository.save(alert);
    }


    @Transactional
    public void checkRules(Readings reading) {
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
