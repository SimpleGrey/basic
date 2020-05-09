package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Date 2020/3/4 13:09
 * @Created by zhanqian
 * @Description TODO
 */
public class KafkaConsumerDemo {
    private final KafkaConsumer<String, String> consumer;

    private KafkaConsumerDemo() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop-master:9092,hadoop-slave1:9092,hadoop-slave2:9092");
        props.put("group.id", "test2");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<String, String>(props);
    }

    //      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG ->
    void consume() {
        consumer.subscribe(Arrays.asList("app-action-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("I'm coming");
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    public static void main(String[] args) {
        KafkaConsumerDemo kafkaConsumerDemo = new KafkaConsumerDemo();
        kafkaConsumerDemo.consume();
    }
}
