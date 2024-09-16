package com.example.demo.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TimerController {

    public TimerController(MeterRegistry registry) {
        Timer timer = registry.timer("Time for operation test");
        timer.record(() -> {
            int sum = 0;
            for (int i = 0; i <= 1000; i++) {
                sum += i;
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
