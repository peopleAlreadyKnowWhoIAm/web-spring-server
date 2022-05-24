package ua.lpnu.students.labs.restapp.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import ua.lpnu.students.labs.restapp.logic.DecorationService;

@Path("/")
public class Endpoint {
    @Autowired
    DecorationService decorationService;

	@GET
    public String getTest(){
        decorationService.writeA();
        return "test";
    }
}
