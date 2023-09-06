package com.project.mqtt;// TemperatureService.java

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
@RequiredArgsConstructor
public class TemperatureService {

    private final MqttClient mqttClient;

    // 메시지가 도착하면 이 메서드가 호출됩니다.
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void processTemperatureData(Message<String> message) throws MqttException {
        String payload = message.getPayload();
        int temperature = Integer.parseInt(payload);
        if (temperature >= 100) {
            System.out.println("Warning: High temperature detected!");
            if(mqttClient != null && mqttClient.isConnected()){
                MqttMessage alertMessage = new MqttMessage("Warning: High temperature detected!".getBytes());
                mqttClient.publish("temperature_alert", alertMessage);
            }
        }
    }
}
