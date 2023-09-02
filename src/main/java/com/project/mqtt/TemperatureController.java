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

    @GetMapping("/publish")
    public String publishTemperature(){
        temperatureScheduler.publishTemperature();
        return "Temperature published successfully.";
    }
}
