package com.project.mqtt;

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttPublisher {

    private final MqttClient mqttClient;

    private int temperature = 0;  // 초기 온도 설정

    // 클라이언트의 요청에 따라 온도를 증가시키는 메서드
    public void startTemperatureIncrease() {
        try {
            temperature += 1;  // 온도 1도 증가

            MqttMessage message = new MqttMessage();
            message.setPayload(String.valueOf(temperature).getBytes());

            mqttClient.publish("sensor/temperature", message);

            // 온도가 100도에 도달했다면 클라이언트에 알림을 보냄
            if (temperature >= 100) {
                // 클라이언트에 알림을 보내는 로직 (예: WebSocket, HTTP 등)
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // "완료" 버튼을 누를 경우 온도를 초기화하는 메서드
    public void resetTemperature() {
        temperature = 0;  // 온도 초기화
    }
}