package kafkaconsume;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by firstsword on 2019/3/26.
 */
public class KfkTopic {
    public static void main(String[] args) {
        KafkaConsumer consumer = new KafkaConsumer(getProp("192.168.21.90:9092", ""));
        Map<String, List<PartitionInfo>> map = consumer.listTopics();
        System.out.println(map.size());
        System.out.println(map.keySet().contains("ITOA_TO_HUB_REQ3"));

    }

    public static Map<String, Object> getProp(String servers, String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", servers);
        //props.put("group.id", "idea_4");
        //props.put("auto.offset.reset", "earliest");
        //props.put("enable.auto.commit", true);
        //props.put("auto.commit.interval.ms", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("retries", 0);
        //"message.timeout.ms", 3000
        props.put("session.timeout.ms", 10000);
        props.put("fetch.max.wait.ms", 10000);
        props.put("request.timeout.ms", 11000);
        return props;
    }
}
