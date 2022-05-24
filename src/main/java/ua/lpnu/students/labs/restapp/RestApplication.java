package ua.lpnu.students.labs.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.lpnu.students.labs.restapp.logic.DecorationService;
import ua.lpnu.students.labs.restapp.configuration.JerseyConfig;

@SpringBootApplication(scanBasePackageClasses = { 
    JerseyConfig.class, 
    DecorationService.class,
}) 
public class RestApplication {
    public static void main(String... args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
