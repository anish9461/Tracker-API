package project.truckerapi.repository;

import org.springframework.data.repository.CrudRepository;
import project.truckerapi.entity.Readings;

public interface ReadingsRepository extends CrudRepository<Readings, String> {
}
