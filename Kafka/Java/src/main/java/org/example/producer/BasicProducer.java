package org.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.consumer.BasicConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class BasicProducer {
    private final static Logger logger = LoggerFactory.getLogger(BasicConsumer.class);
    private final static String TOPIC_NAME = "jinyes";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String msgValue = "Hello Kafka";
        for (int i=0; i<100; i++){
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, msgValue +' '+ i);
            producer.send(record);
            logger.info("{}", record);
        }
        producer.flush();
        producer.close();
    }

}
