package project.truckerapi.service;

import project.truckerapi.entity.Readings;

import java.util.List;

public interface ReadingsService {
    List<Readings> findAllReadings();
    Readings createReadings(Readings reading);

}
