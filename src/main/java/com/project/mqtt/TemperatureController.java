package com.project.mqtt;

import com.project.config.TemperatureScheduler;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureScheduler temperatureScheduler;

    @GetMapping("/start")
    public String startPublishing() {
        temperatureScheduler.setActive(true);
        return "Temperature publishing started.";
    }

    @GetMapping("/stop")
    public String stopPublishing() {
        temperatureScheduler.setActive(false);
        return "Temperature publishing stopped.";
    }
}
