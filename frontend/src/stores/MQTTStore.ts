import {connect, MqttClient} from "mqtt"
import {defineStore} from "pinia";
import {ref, Ref, UnwrapRef} from "vue";
import config from "../../config";
//https://www.npmjs.com/package/mqtt
export class MQTTOptions{
    qos: number=2;
}

/**
 * MQTT client used to connect and respond to topics. A single callback can be registered for each subscribed topic.
 * Topic callbacks are lost when the object gets destroyed (page refreshed).
 */
export const useMQTTStore = defineStore('mqtt', ()=>{
    const client: Ref<UnwrapRef<MqttClient|undefined>> = ref<MqttClient|undefined>(undefined);
    const remote: String = config.MQTT.REMOTE;
    const port: number = config.MQTT.PORT;

    const subscriptions: Map<String,{ (message: string):void }> = new Map<String,{ (s: string): void }>();

    function onLoad(){
        client.value = connect('tcp://'+remote + ':' + port) //TODO: test if correct
        client.value.on('message',receiveMessage)
    }

    function receiveMessage(topic: string, message: string, packet: Object){
        if (subscriptions.has(topic)){
            subscriptions.get(topic)!(message); //Execute callback registered in subscription callbacks
        }
    }

    function subscribe( topic: string, options: MQTTOptions, callback: { (message: string):void}){
        if (!client.value) return; //TODO: throw error or not?
        client.value.subscribe(topic, Object.assign(options), (err, granted) =>{
            if (err) console.log(err);
            console.log(granted);
            subscriptions.set(topic, callback);
        });
    }
    function unsubscribe(topic: string){
        if (!client.value) return; //TODO: throw error or not?
        client.value.unsubscribe(topic,undefined,(err)=>{
            if (err) console.log(err);
        });
        subscriptions.delete(topic);
    }

    onLoad()
    return {client, subscribe, unsubscribe};
})