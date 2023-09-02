package com.project.mqtt;// TemperatureService.java

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class TemperatureService {

    // 메시지가 도착하면 이 메서드가 호출됩니다.
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void processTemperatureData(Message<String> message) {
        String payload = message.getPayload();
        int temperature = Integer.parseInt(payload);
        if (temperature >= 100) {
            System.out.println("Warning: High temperature detected!");
        }
    }
}
