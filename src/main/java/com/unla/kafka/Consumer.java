package com.unla.kafka;

import com.unla.kafka.constants.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {

        // Create logger for class
        final Logger logger = LoggerFactory.getLogger(Consumer.class);

        // Create variables for strings
        final String bootstrapServers = Constants.BOOTSTRAP_SERVERS;
        final String consumerGroupID = Constants.CONSUMER_GROUP_ID;

        // Create and populate properties object
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);//Especifico el servidor de arranque
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//El deserializador de clave
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//El deserializador de valor
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupID); //EL grupo de consumidores
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//Como el grupo de consumidores puede NO existir hasta este momento, se restablece

        // Create consumer
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);

        // Subscribe to topic(s)
        consumer.subscribe(Arrays.asList("sample-topic"));//aunque por ahora solo voy a escuchar un tema le paso una colección

        // Poll and consumer records
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));//Para obtener los datos del tema (devuelve un ConsumerRecord)
            //Luego de cosumir los datos ya no estarán disponibles
            for (ConsumerRecord record: records){
                logger.info("\nReceived new metadata. \n" +
                            "Key: " + record.key() +
                            ", Value: " + record.value() +
                            ", Topic: " + record.topic() +
                            ", Partition: " + record.partition() +
                            ", Offset: " + record.offset() + "\n"
                           );
            }
        }
    }
}
