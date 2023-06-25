package be.ita.toernooimanager.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j@ActiveProfiles("test")
//@WithMockUser(authorities = {"admin_read", "admin_write", "roles_read", "logon"})
public class MQTTServiceTest {
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
    private final String publisherId = UUID.randomUUID().toString();

    @Autowired
    private MQTTService mqttService;

    MqttClient receiver;

    Boolean receivedMessage = false;
    @BeforeEach
    void setUp() throws MqttException {
        String address = "tcp://" + this.remote + ":" + this.port;
        this.receiver= new MqttClient(address, publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(this.autoReconnect);
        options.setCleanSession(this.cleanSession);
        options.setConnectionTimeout(this.timeout);
        assertDoesNotThrow(()->this.receiver.connect(options));
    }

    @AfterEach
    void tearDown() throws MqttException {
        if (this.receiver.isConnected()){
            this.receiver.disconnect();
        }
        receivedMessage = false;
    }

    @Test
    public void publishMessageTest() throws MqttException, InterruptedException {
        String testTopic = "/ITA_Testing_UnitTests/publish";
        String testMessage = "This is a test message";

        receiver.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {}

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                assertEquals(testTopic,topic);
                assertEquals(testMessage, message.toString());
                receivedMessage = true;
                log.info("Received MQTT {topic:" + topic+",message:"+message + "}");
            }

            public void deliveryComplete(IMqttDeliveryToken token) {}
        });

        receiver.subscribe(testTopic);

        this.mqttService.publish(testTopic,testMessage,2); //Use QOS exactly once to make sure we expect the message
        for (int i =0; i < 11; i++){
            if (this.receivedMessage){
                break;
            }
            Thread.sleep(100); //wait 100 ms
            if (i == 10){
                fail("No answer received within 1 second");
            }
        }
     }

}
