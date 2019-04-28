package kafkaMirror;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Created by firstsword on 2019/3/4.
 */
public class KafkaMirror {

    public static void main(String[] args) throws Exception {
        Args arg = new Args();
        arg.parseArgs(args);

        String confPath = arg.confPath;
        System.out.println(confPath);
        Config conf = Config.getConfig(confPath);
        System.out.println("timeout:"+conf.timeout);

        Producer<byte[], byte[]> producer = createKfkProducer(conf);
        KafkaConsumer<byte[], byte[]> consumer = createKfkConsumer(conf);


        int timeout = conf.timeout;
        int num = 0, i = 0;

        while (true) {
            ConsumerRecords<byte[], byte[]> records = consumer.poll(1000);

            int count = records.count();

            if (records == null || count <= 0) {
                i++;
            } else {
                i = 0;
            }

            if (i >= timeout) break;

            num += count;

            //System.out.println(records.count());
            for (ConsumerRecord<byte[], byte[]> record : records) {
                System.out.printf("offset = %d, key = %s\n", record.offset(), new String(record.key()));
                producer.send(new ProducerRecord<>(conf.targetTopic, record.key(), record.value()));
            }
        }

        producer.flush();
        producer.close();
        consumer.close();
        System.out.println("total:" + num);
    }

    public static Producer<byte[], byte[]> createKfkProducer(Config conf) {
        Properties props = new Properties();
        props.put("bootstrap.servers", conf.targetServers);
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.ByteArraySerializer");

        Producer<byte[], byte[]> producer = new KafkaProducer<byte[], byte[]>(props);
        return producer;
    }

    public static KafkaConsumer<byte[], byte[]> createKfkConsumer(Config conf) {
        Properties props = new Properties();
        props.put("bootstrap.servers", conf.srcServers);
        //props.put("group.id", "my-kafka-mirror-test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "10");
        props.put("session.timeout.ms", "30000");

        props.put("auto.offset.reset", "earliest");
        props.put("group.id", UUID.randomUUID().toString());
        props.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(conf.srcTopic));

        return consumer;
    }
}
