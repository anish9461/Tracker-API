# CarTracker
## A Fleet Management API


### Table of Content
**[Overview](#overview) 2**

**[Requirements](#requirements) 2**

> [Phase 1 : REST API, JPA, Testing](#phase-1-rest-api-jpa-testing) 2
> 
> [Phase 2 : AWS Services, Docker,
> Jenkins](#phase-2-aws-services-docker-jenkins) 3

**[Data Model](#data-model) 3**

> [Entities and Attributes](#entities-and-attributes) 3
> 
> [Relationships](#relationships) 6

**[Milestones](#milestones) 6**

> [REST API Calls](#rest-api-calls) 6
> 
> [Vehicle](#vehicle) 6
> 
> [Readings](#readings) 6
> 
> [Alerts](#alerts) 7
> 
> [Tests](#tests) 7
> 
> [Unit Tests](#unit-tests) 7
> 
> [Integration Tests](#integration-tests) 7
> 
> [Email and SMS notification using AWS SES and AWS
> SNS](#email-and-sms-notification-using-aws-ses-and-aws-sns) 7
> 
> [AWS SES](#aws-ses) 7
> 
> [AWS SES](#aws-ses-1) 8
> 
> [AWS VPC, Docker and Jenkins](#aws-vpc-docker-and-jenkins) 8
> 
> [AWS VPC](#aws-vpc) 8
> 
> [AWS Docker](#aws-docker) 9
> 
> [AWS Jenkins](#aws-jenkins) 9
> 
> [Deployment Links](#deployment-links) 9
> 
> [Swagger-UI](#swagger-ui) 9
> 
> [GitHub Repo](#github-repo) 9

# 

# Overview

Fleet Management is an administrative approach that allows companies to
organize and coordinate work vehicles with the aim to improve
efficiency, reduce cost and provide compliance with government
regulations. Fleet management can be used to manage fuel and maintenance
cost, improve driver safety, have longer vehicle life spans, have
customer retention rates. Keeping these points into consideration, this
project provides the necessary APIs to the client user to assess vehicle
performance and monitor the vehicle.

In this project, the mocker API provided by Egen solutions mocks an
actual physical device on the vehicle which would send the vehicle
attributes to the requested backend application. The mocker sends a list
of vehicles initially to store the vehicle details in the database and
then would post the readings of the sensor to the API.

The project is divided into two phases. The first phase is to develop a
REST API in spring boot with unit and integration tests for the
requirements listed in the next section. The second phase of the project
is to deploy the application on AWS with Docker and Jenkins to automate
the process.

# Requirements

## **Phase 1 : REST API, JPA, Testing**

The Technology stacks used to accomplish the below requirements are
Java, Spring Boot, Spring Data, JUnit Testing, Swagger UI.

1.  > Develop the Following REST API calls for ingesting the values from
    > mocker API
    
    1.  > Load vehicle details in bulk via a PUT /vehicles endpoint
    
    2.  > If the vehicle with same Vehicle ID exists, update the
        > database
    
    3.  > Ingest readings from these vehicles via a POST /readings

2.  > Create alerts with the given priority when the following rules are
    > triggered
    
    1.  > Rule : engineRpm \> readlineRpm, Priority : HIGH
    
    2.  > Rule : fuelVolume \< 10% of maxFuelVolume, Priority : MEDIUM
    
    3.  > Rule : tire pressure of any tire \< 32psi or \> 36psi ,
        > Priority : LOW
    
    4.  > Rule: engineCoolantLow is true or checkEngineLightOn is true,
        > Priority : LOW

3.  > Develop ability to send an email and SMS to the user when HIGH
    > alerts are triggered for a vehicle

4.  > Develop REST end-points for the following:
    
    1.  > Fetch details of all the vehicles like Vin, make, model, year
    
    2.  > Fetch HIGH alerts with last 2 hours for all the vehicles and
        > ability to sort list of vehicles based on it
    
    3.  > Ability to list vehicle’s geolocation within the last 30
        > minutes on a map.
    
    4.  > Ability to list a vehicles all historical alerts

## **Phase 2 : AWS Services, Docker, Jenkins**

The Technology stacks used to accomplish the below requirements are AWS
EC2, AWS VPC, Docker, Docker Swarm, Jenkins, AWS Route53, AWS SES and
AWS SNS.

1.  > Launch one AWS VPC with subnets and security groups

2.  > Configure Docker Swarm cluster on AWS VPC

3.  > Configure Jenkins to run a build job after every commit on your
    > repo and deploy to aws cluster

4.  > User Route53 to configure DNS and Health checks for your service

5.  > Use AWS SES and SNS to send emails and SMS

# Data Model

The database used to accomplish this project is a relational database
MySQL hosted on AWS RDS. The data model for this project is shown below.

## **Entities and Attributes**

<table>
<thead>
<tr class="header">
<th><strong>Objects</strong></th>
<th><strong>Description</strong></th>
<th><strong>Field Type</strong></th>
<th><strong>Properties</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><ol type="1">
<li><blockquote>
<p><strong>Vehicle</strong></p>
</blockquote></li>
</ol></td>
<td>Vehicle Details</td>
<td></td>
<td></td>
</tr>
<tr class="even">
<td>vin</td>
<td>Vehicle ID as primary key</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td>model</td>
<td>Model of the vehicle</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td>make</td>
<td>Make of the vehicle</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td>year</td>
<td>Year of the vehicle purchased</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td>readlineRpm</td>
<td>Readline RPM of the vehicle</td>
<td>Double</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td>maxFuelVolume</td>
<td>Maximum fuel volume of the vehicle</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td>lastServiceDate</td>
<td>Last service date of the vehicle</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><ol start="2" type="1">
<li><blockquote>
<p><strong>Readings</strong></p>
</blockquote></li>
</ol></td>
<td>Readings of the Vehicle</td>
<td></td>
<td></td>
</tr>
<tr class="even">
<td><blockquote>
<p>readingsID</p>
</blockquote></td>
<td>Primary key of the table</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>vin</p>
</blockquote></td>
<td>Vehicle id of the vehicle (FK)</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>TireID</p>
</blockquote></td>
<td>Tire ID of the vehicle (FK)</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>fuelVolume</p>
</blockquote></td>
<td>Volume of the fuel</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>speed</p>
</blockquote></td>
<td>Speed of the vehicle</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>engineHP</p>
</blockquote></td>
<td>Engine HP of the vehicle</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>checkEngineLightOn</p>
</blockquote></td>
<td>Check if lights are On</td>
<td>bit</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>engineCoolantLow</p>
</blockquote></td>
<td>Check if engine coolant is low</td>
<td>bit</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>cruiseControlOn</p>
</blockquote></td>
<td>Check if cruise Control is On</td>
<td>bit</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>engineRpm</p>
</blockquote></td>
<td>Engine RPM of the vehicle</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>latitude</p>
</blockquote></td>
<td>Latitude location value of the vehicle</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>longitude</p>
</blockquote></td>
<td>Longitude location value of the vehicle</td>
<td>double</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>timestamp</p>
</blockquote></td>
<td>Timestamp of the reported value</td>
<td>datetime</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>Created</p>
</blockquote></td>
<td>Time the value was received</td>
<td>datetime</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p><strong>3.Tires</strong></p>
</blockquote></td>
<td>The Tire pressure of the vehicle</td>
<td></td>
<td></td>
</tr>
<tr class="odd">
<td><blockquote>
<p>tireID</p>
</blockquote></td>
<td>Primary key of the table</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>frontLeft</p>
</blockquote></td>
<td>Front left tire pressure</td>
<td>Int</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>frontRight</p>
</blockquote></td>
<td>Front right tire pressure</td>
<td>Int</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>rearLeft</p>
</blockquote></td>
<td>Rear left tire pressure</td>
<td>Int</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>rearRight</p>
</blockquote></td>
<td>Rear right tire pressure</td>
<td>Int</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p><strong>4. Alerts</strong></p>
</blockquote></td>
<td>The Alerts for the vehicle</td>
<td></td>
<td></td>
</tr>
<tr class="odd">
<td><blockquote>
<p>AlertsID</p>
</blockquote></td>
<td>Primary key of the table</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>ReadingsID</p>
</blockquote></td>
<td>Foreign key</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>Priority</p>
</blockquote></td>
<td>Priority of the alert</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>Rule</p>
</blockquote></td>
<td>Rule violated</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="odd">
<td><blockquote>
<p>Vin</p>
</blockquote></td>
<td>Vehicle ID of the vehicle</td>
<td>Varchar</td>
<td>NOT NULL</td>
</tr>
<tr class="even">
<td><blockquote>
<p>timestamp</p>
</blockquote></td>
<td>Timestamp of the alert created</td>
<td>datetime</td>
<td>NOT NULL</td>
</tr>
</tbody>
</table>

![](media/image1.png)

## **Relationships**

  - > The relationship between vehicle and Readings is one to many
    > optional. i.e. One vehicle can have none or many readings

  - > The relationship between Readings and Tires is One to One as every
    > reading will have tires value

  - > The relationship between Readings and Alerts is one to many
    > optional. i.e. One reading may or may not have many alerts.

# Milestones

## **REST API Calls**

> REST end-points provided for the application are:

###  **Vehicle**

The following end-points are provided for vehicle:

  - > **GET:** /api/vehicles → Fetch details for all the vehicles

  - > **PUT :** /api/vehicles → Create or update the vehicle record

  - > **GET :** /api/vehicles/location/{vehicleId} → Get all the
    > locations in longitudes and latitudes for the vehicle for last 30
    > mins

###  **Readings**

The following end-points are provided for readings:

  - > **GET:** /api/readings → Fetch all the readings of the sensor data

  - > **POST:** /api/readings → Create all the readings of the sensor
    > data in the database

### **Alerts**

The following end-points are provided for alerts:

  - > **GET:** /api/alerts/high → fetch all the HIGH priority alerts for
    > last 2 hours

  - > **GET :** /api/vehicles/alerts/{vehicleId} → Get all the alerts
    > for the vehicle

## **Tests**

### **Unit Tests**

Unit Tests have been provided for the following services:

  - > Vehicle Service

  - > Readings Service

###  **Integration Tests**

Integration Tests have been provided for the following controllers:

  - > Vehicle Controller

  - > Readings Controller

## **Email and SMS notification using AWS SES and AWS SNS**

###  **AWS SES**

> AWS SDK was used to implement the email notification service for the
> alerts that have HIGH priority.
> 
> The domain name and email id was verified in AWS console for
> anishnesarkar.com

<table>
<tbody>
<tr class="odd">
<td><p>private void SendEmail(){</p>
<p>BasicAWSCredentials b = new BasicAWSCredentials(env.getProperty("aws.AccessKey"), env.getProperty("aws.SecretKey"));</p>
<p>AWSCredentials credentials = null;</p>
<p>try {</p>
<p>credentials = b;</p>
<p>} catch (Exception e) {</p>
<p>throw new AmazonClientException(</p>
<p>"Wrong credentials ", e</p>
<p>);</p>
<p>}</p>
<p>AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);</p>
<p>Region region = Region.<em>getRegion</em>(AWS_REGION);</p>
<p>client.setRegion(region);</p>
<p>SendEmailRequest request = new SendEmailRequest()</p>
<p>.withDestination(</p>
<p>new Destination().withToAddresses(email_to))</p>
<p>.withMessage(new Message()</p>
<p>.withBody(new Body()</p>
<p>.withText(new Content()</p>
<p>.withCharset("UTF-8").withData(email_body)))</p>
<p>.withSubject(new Content()</p>
<p>.withCharset("UTF-8").withData(subject)))</p>
<p>.withSource(email_from);</p>
<p>client.sendEmail(request);</p>
<p>System.<em>out</em>.println("Email sent");</p>
<p>}</p></td>
</tr>
</tbody>
</table>

### **AWS SES**

> AWS SDK was used to implement the sms notification service for the
> alerts that have HIGH priority.
> 
> The phone number was verified on AWS SES console and topic ARN was
> created for topic “trucker”

<table>
<tbody>
<tr class="odd">
<td><p>private void SendSMS(){</p>
<p>AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(env.getProperty("aws.AccessKey"), env.getProperty("aws.SecretKey")));</p>
<p>AmazonSNS amazonSNS = AmazonSNSClientBuilder.<em>standard</em>()</p>
<p>.withCredentials(awsCredentialsProvider)</p>
<p>.withRegion(AWS_REGION)</p>
<p>.build();</p>
<p>PublishResult result = amazonSNS.publish(env.getProperty("aws.arn"),"This is from Java app");</p>
<p>System.<em>out</em>.println("SMS message ID "+result.getMessageId());</p>
<p>}</p></td>
</tr>
</tbody>
</table>

## **AWS VPC, Docker and Jenkins**

### **AWS VPC**

  - > The virtual private cloud was established on AWS with two security
    > groups (a public and private).

  - > Two EC2 nodes were assigned for each security group in the VPC
    > with subnets.

  - > An internet gateway was provided for master node (node1) to have
    > access from outside the private cloud

  - > The node2 could be accessed only from node1

###  **AWS Docker**

  - > Docker was installed on node1(master) and node2

  - > Docker swarm was initialized for node1 and node2 was joined as
    > worker node

  - > Portainer was used to visualize the docker services and add the
    > secret keys

  - > The docker image was created and a .sh file to copy application
    > properties from the secrets of the docker service

  - > The docker service is executed in docker swarm with the
    > application jar file

![](media/image3.png)

###  **AWS Jenkins**

  - > A docker user is created for Jenkins profile

  - > Docker hub credentials are added on the Jenkins to push the docker
    > image to the docker hub repository

  - > A pipeline is created in Jenkins with the GitHub repository AWS
    > branch

  - > The pipeline is automated to run for every commit on the AWS
    > branch

## **Deployment Links**

### **Swagger-UI**

> The swagger-UI for the application is hosted on AWS and can be
> accessed on the following URL
> 
> [<span class="underline">http://tracker.anishnesarkar.com/api/swagger-ui.html</span>](http://tracker.anishnesarkar.com/api/swagger-ui.html)

### **GitHub Repo**

> The GitHub repository for the project is :
> 
> [<span class="underline">https://github.com/anish9461/Tracker-API</span>](https://github.com/anish9461/Trucker-API)

