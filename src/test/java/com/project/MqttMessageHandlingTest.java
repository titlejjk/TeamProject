package com.project;

import com.project.mqtt.MqttPublisher;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MqttMessageHandlingTest {

    //테스트할 MqttPublisher Class
    @InjectMocks
    private MqttPublisher mqttPublisher;

    //MQTT 클라이언트 Mock 객체
    @Mock
    private MqttClient mqttClient;

    //Mock 객체를 초기화하기 위한 메서드
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    //메세지 발행 테스트
    @Test
    @DisplayName("MQTT pub/sub간의 메세지 발행 테스트")
    public void testPublishMessage() throws Exception{

        //Given
        MqttMessage message = new MqttMessage();
        message.setPayload("1".getBytes());

        //When
        mqttPublisher.startTemperatureIncrease();//메서드 실행

        //Then
        verify(mqttClient).publish(eq("sensor/temperature"), any(MqttMessage.class));

    }
    //온도 초기화 메서드 테스트
    @Test
    public void testResetTemperature(){
        //온도를 초기화하는 메서드
        mqttPublisher.resetTemperature();
    }
}
