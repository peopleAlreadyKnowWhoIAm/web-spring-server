package ua.lpnu.students.labs.restapp;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import ua.lpnu.students.labs.restapp.controller.MainRestController;


@Component
class JersayConfig extends ResourceConfig{
    public JersayConfig(){
        register(MainRestController.class);
        System.out.println("kdjoskksdo");
    }
}
