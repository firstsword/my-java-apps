package kafkaconsume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by firstsword on 2019/3/14.
 */
public class ConsumerThread extends Thread {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;


    public ConsumerThread(String threadName, Properties props, String... topics) {
        super(threadName);

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topics));
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(1000);

                System.out.println("调用了");

                for (ConsumerRecord<String, String> record : records) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Thread.currentThread().getName()+"\t");
                    sb.append("par="+record.partition()+"\t");
                    sb.append("offset="+record.offset()+"\t");
                    sb.append("key="+record.key()+"\t");
                    sb.append("value="+record.value()+"\t");

                    System.out.println(sb.toString());
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
}
