package com.project.mqtt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttSubscriber {

    //MqttConfig에 있는 MqttPahoMessageDrivenChannelAdapter의존성 주입
    private final MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void receiveMessage(String payload){
        //payload처리
        System.out.println("Received message : " + payload);
    }
}
