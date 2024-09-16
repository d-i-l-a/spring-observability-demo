package com.example.demo.controller;

import com.example.demo.component.CustomMetricsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloWorldController {


    private final CustomMetricsService customMetricsService;

    public HelloWorldController(CustomMetricsService customMetricsService) {
        this.customMetricsService = customMetricsService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/flaky")
    public String runFlaky() {
        double random = Math.random();
        if (random < 0.3) {
            customMetricsService.incrementCustomMetric();
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "flaky endpoint failed");
        }
        return "counted";
    }


}
