package com.project.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration //Spring 설정 클래스 명시
public class MqttConfig {

    /*
        MqttConnectOptions빈을 생성
        MQTT브로커에 연결할 때 사용할 옵션을 설정
     */
    @Bean
    public MqttConnectOptions mqttConnectOptions(){
        MqttConnectOptions option = new MqttConnectOptions();
        /*
            MQTT브로커의 URI를 설정. 로컬 호스트의 1883포트사용
            여러개의 URI설정도 가능하다.
         */
        option.setServerURIs(new String[]{"tcp://localhost:1883"});
        return option;
    }
    /*
        MqttPahoClientFactory빈 생성
        MQTT클라이언트 인스턴스를 생성할 때 사용
     */
    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory(){
        //DefaultMqttPahoClientFactory클래스는 MqttPahoClientFactory인터페이스를 구현
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        //생성한 MqttConnectOptions빈을 사용하여 연결 옵션을 설정
        factory.setConnectionOptions(mqttConnectOptions());
        return factory;
    }

    //tcp://localhost:1883를 쓰는 MQTT브로커 URL로 "someClientId"는 MQTT클라이언트 ID
    @Bean
    public MqttClient mqttClient()throws MqttException{
        return new MqttClient("tcp://localhost:1883", "someClientId");
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter(MqttPahoClientFactory mqttPahoClientFactory){
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("someClientId", mqttPahoClientFactory, "someTopic");
        adapter.setCompletionTimeout(5000);  // 타임아웃 설정
        adapter.setConverter(new DefaultPahoMessageConverter());  // 메시지 컨버터 설정
        adapter.setQos(1);// Quality of Service 설정
        adapter.setOutputChannelName("mqttInputChannel");//출력 채널 설정
        return adapter;
    }
}
