package ua.lpnu.students.labs.restapp.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class MainRestController {
	@GET
    public String getTest(){
        return "test";
    }
}
