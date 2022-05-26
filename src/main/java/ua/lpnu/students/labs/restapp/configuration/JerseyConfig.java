package ua.lpnu.students.labs.restapp.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import ua.lpnu.students.labs.restapp.controller.Endpoint;

/**
 * Class for config Jersey.
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(Endpoint.class);
    }
}
