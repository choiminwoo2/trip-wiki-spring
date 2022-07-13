package com.trip.tripwiki.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;

@Controller
public class RootController implements ErrorController {

    @GetMapping("/error")
    public String redirectRoot() throws FileNotFoundException {
        return "index.html";
    }
}
