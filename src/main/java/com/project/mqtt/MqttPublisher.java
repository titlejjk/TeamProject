//package com.project.mqtt;
//
//import lombok.RequiredArgsConstructor;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MqttPublisher {
//
//    private final MqttClient mqttClient;
//
//    @Autowired
//    public MqttPublisher(MqttClient mqttClient){
//        this.mqttClient = mqttClient;
//    }
//
//    // 5초마다 이 메서드를 실행
//    @Scheduled(fixedRate = 5000)
//    public void publishTemperature() {
//        try {
//            int temperature = generateRandomTemperature(); // 랜덤 온도 생성
//            publish("temperature", String.valueOf(temperature));
//        } catch (MqttException e) {
//            System.out.println("Failed to publish temperature: " + e.getMessage());
//        }
//    }
//
//    private void publish(String topic, String payload) throws MqttException {
//        if (!mqttClient.isConnected()) {
//            mqttClient.connect();
//        }
//        MqttMessage message = new MqttMessage(payload.getBytes());
//        mqttClient.publish(topic, message);
//    }
//
//    private int generateRandomTemperature() {
//        return (int) (Math.random() * 150); // 0 ~ 149 사이의 랜덤 온도를 생성
//    }
//}
