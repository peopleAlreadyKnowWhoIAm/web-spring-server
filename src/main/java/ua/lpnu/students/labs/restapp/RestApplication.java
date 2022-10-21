package ua.lpnu.students.labs.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.lpnu.students.labs.restapp.controller.DecorationController;
import ua.lpnu.students.labs.restapp.controller.ViewController;
import ua.lpnu.students.labs.restapp.dataaccess.database.ElectricDecorationRepository;
import ua.lpnu.students.labs.restapp.logic.DecorationService;

/**
 * Intresting......
 */
@SpringBootApplication(scanBasePackageClasses = {
    DecorationService.class,
    ElectricDecorationRepository.class,
    ViewController.class,
    DecorationController.class,
})
public class RestApplication {
    public static void main(String... args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
