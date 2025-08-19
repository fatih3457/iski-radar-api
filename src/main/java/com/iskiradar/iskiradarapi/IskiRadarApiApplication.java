package com.iskiradar.iskiradarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IskiRadarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IskiRadarApiApplication.class, args);
    }

}