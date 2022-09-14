package com.unla.kafka;

import com.unla.kafka.constants.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {

        // Create properties object for Producer
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS); //prop.setProperty("bootstrap.servers", "127.0.0.1:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //prop.setProperty("key.serializer", StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //prop.setProperty("value.serializer", StringSerializer.class.getName());

        // Create the Producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        // Create the ProducerRecord
        ProducerRecord<String, String> record = new ProducerRecord<>("sample-topic", "key1", "Value_texto-mensaje1");

        // Send Data - Asynchronous
        producer.send(record);

        // Flush and close producer
        producer.flush();
        producer.close();
    }

}
