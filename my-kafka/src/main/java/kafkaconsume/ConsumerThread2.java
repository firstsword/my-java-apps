package kafkaconsume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by firstsword on 2019/3/14.
 */
public class ConsumerThread2 extends Thread {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;

    private final List<TopicPartition> partitions = new ArrayList<>();



    public ConsumerThread2(String threadName, Map<String, Object> props, String... topics) {
        super(threadName);

        consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Arrays.asList(topics));
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topics[0]);
        for (PartitionInfo partitionInfo : partitionInfos) {
            partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
        }
        consumer.assign(partitions);
        System.out.println(partitions);
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {

                ConsumerRecords<String, String> records = null;

                Set<TopicPartition> assignment = consumer.assignment();
                System.out.println(assignment);
                Map<TopicPartition, Long> endOffsets = consumer.endOffsets(assignment);
                System.out.println(endOffsets);

                for (TopicPartition par : assignment) {
                    long position = consumer.position(par);
                    System.out.println(position);
                }
                //log.trace("Reading to end of log offsets {}", endOffsets);

                while (!endOffsets.isEmpty()) {
                    Iterator<Map.Entry<TopicPartition, Long>> it = endOffsets.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<TopicPartition, Long> entry = it.next();
                        if (consumer.position(entry.getKey()) >= entry.getValue())
                            it.remove();
                        else {
                            records = consumer.poll(Integer.MAX_VALUE);
                            break;
                        }
                    }
                }
                //System.out.println(consumer);

                //ConsumerRecords<String, String> records = consumer.poll(1000);

                if (records != null) {
                    for (ConsumerRecord<String, String> record : records) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Thread.currentThread().getName() + "\t");
                        sb.append("par=" + record.partition() + "\t");
                        sb.append("offset=" + record.offset() + "\t");
                        sb.append("key=" + record.key() + "\t");
                        sb.append("value=" + record.value() + "\t");

                        System.out.println(sb.toString());
                    }
                }


            }
        } catch (Exception e) {
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
        }
    }

    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }


    private void readToLogEnd() {
        //log.trace("Reading to end of offset log");


    }
}
