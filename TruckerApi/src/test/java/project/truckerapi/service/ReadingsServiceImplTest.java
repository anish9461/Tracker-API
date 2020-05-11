package project.truckerapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ReadingsServiceImplTest {

    @TestConfiguration
    static class ReadingsServiceImplTestConfiguration{

        @Bean
        public ReadingsService getService(){
            return new ReadingsServiceImpl();
        }
    }

    @Autowired
    private ReadingsService readingsService;

    @Test
    public void findAllReadings() {
    }

    @Test
    public void createReadings() {
    }
}