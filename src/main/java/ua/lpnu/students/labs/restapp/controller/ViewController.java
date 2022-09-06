package ua.lpnu.students.labs.restapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class ViewController {
    @GetMapping
    public ModelAndView response() {
        return new ModelAndView("index", HttpStatus.OK);
    }
}