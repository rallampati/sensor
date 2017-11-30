package com.egen.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.egen.sensor.repository"})
public class SensorEmulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorEmulatorApplication.class, args);
    }
}
