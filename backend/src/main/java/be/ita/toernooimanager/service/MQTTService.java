package be.ita.toernooimanager.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class MQTTService {
    @Value("${mqtt.address:broker.hivemq.com}")
    protected String remote;

    @Value("${mqtt.port:1883}")
    protected int port;
    @Value("${mqtt.auto-reconnect:true}")
    protected boolean autoReconnect;
    @Value("${mqtt.clean-session:true}")
    protected boolean cleanSession;
    @Value("${mqtt.timeout:10}")
    protected int timeout;
    @Value("${mqtt.username:}")
    protected String userName;
    @Value("${mqtt.password:}")
    protected String password;

    /**
     * qos:
     *  0: At most once
     *  1: At least once
     *  2: Exactly once
     */
    @Value("${mqtt.qos:0}")
    protected int qos;


    private String address;
    private final String publisherId = UUID.randomUUID().toString();
    IMqttClient mqttPublisher;

    @PostConstruct
    public void postConstruct(){
        this.address = "tcp://" + this.remote + ":" + this.port;
        try {
            this.mqttPublisher = new MqttClient(this.address, publisherId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(this.autoReconnect);
            options.setCleanSession(this.cleanSession);
            options.setConnectionTimeout(this.timeout);
            mqttPublisher.connect(options);
            log.info("Connected to MQTT remote: " + this.address);
        }catch(Exception e){
            log.info("Could not connect to MQTT remote at " + this.address);
        }
    }

    public boolean publish(String topic, String message){
        return this.publish(topic, message, this.qos);
    }
    public boolean publish(String topic, String message, int qos){
        if (!mqttPublisher.isConnected()) return false;
        if (qos > 2 || qos < 0) return false; //TODO: throw error or not?
        try {
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());
            mqttmessage.setQos(qos);
            mqttmessage.setRetained(false);
            this.mqttPublisher.publish(topic, mqttmessage);
            log.info("Sent MQTT {topic:" + topic+",message:"+message + "}");
        } catch (MqttException me) {
            log.error("ERROR", me);
            return false;
        }
        return true;
    }
}
