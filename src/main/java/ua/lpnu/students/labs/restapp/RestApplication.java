package ua.lpnu.students.labs.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.lpnu.students.labs.restapp.controller.MainRestController;

@SpringBootApplication(scanBasePackageClasses = {MainRestController.class, JersayConfig.class})
public class RestApplication {
    public static void main(String... args){
        SpringApplication.run(RestApplication.class, args);
    }
    
}


