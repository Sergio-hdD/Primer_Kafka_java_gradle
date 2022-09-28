package com.unla.kafka;

import com.unla.kafka.constants.Constants;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    public static void setPropertiesObjectForProducer(Properties properties) {
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS); //prop.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //prop.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //prop.setProperty("value.serializer", StringSerializer.class.getName());
    }

    public static void sendData_Asynchronous(KafkaProducer<String, String> producer, ProducerRecord<String, String> record){
        final Logger logger = LoggerFactory.getLogger(Producer.class); //creo un registrador
        producer.send(record, new Callback() {//Agrego un objeto (interfaz) de devolución de llamada, para que pueda ser llamado cuando se crea necesario
            @Override //Se llamará a este método (onCompletion) una vez el mensaje se escriba con exito el topic de kafka
            public void onCompletion(RecordMetadata metadata, Exception exception) {//Método de finalización (es el único de la clase Callback)
                if (exception == null){//Si el mensaje se escribe con exito el topic de kafka y recibo metadatos
                    //metadata. tiene mucha info, solo uso algunas
                    logger.info("\nReceived record metadata. \n" +
                            "Topic: "+ metadata.topic() + ", partition: " + metadata.partition() +
                            ", Offset: "+ metadata.offset() + " @ Timestamp: " + metadata.timestamp() + "\n"
                    );
                } else {
                    logger.error("Error occurred", exception); //registro el error;
                }
            }
        });
    }
    
    public static void createMessage(String topic, String key, JSONObject jsonObject) {
        // Create properties object for Producer
        Properties prop = new Properties();
        setPropertiesObjectForProducer(prop);

        // Create the Producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        // Create the ProducerRecord
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonObject.toString());

        sendData_Asynchronous(producer, record);

        // Flush and close producer
        producer.flush();
        producer.close();
    }

}
