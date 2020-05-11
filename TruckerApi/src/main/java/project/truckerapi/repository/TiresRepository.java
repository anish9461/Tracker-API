package project.truckerapi.repository;

import org.springframework.data.repository.CrudRepository;
import project.truckerapi.entity.Tires;

public interface TiresRepository extends CrudRepository<Tires, String> {
}
