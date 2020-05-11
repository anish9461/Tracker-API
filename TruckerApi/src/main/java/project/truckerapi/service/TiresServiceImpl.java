package project.truckerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.truckerapi.entity.Tires;
import project.truckerapi.repository.TiresRepository;

@Service
public class TiresServiceImpl implements TiresService {

    @Autowired
    private TiresRepository tiresRepository;

    @Transactional
    public Tires createTires(Tires tires){
        return tiresRepository.save(tires);
    }
}
