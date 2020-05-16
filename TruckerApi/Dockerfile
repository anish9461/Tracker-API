FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/TruckerApi-1.0.0.jar api.jar
ENTRYPOINT ["java","-jar","/api.jar"]