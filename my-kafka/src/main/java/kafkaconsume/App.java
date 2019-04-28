package kafkaconsume;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by firstsword on 2019/3/14.
 */
public class App {
    private static List<ConsumerThread> consumerThreads = new ArrayList<>();


    public static void main(String[] args) throws Exception{
        Args arg = new Args();
        arg.parseArgs(args);

        if (isEmpty(arg.group) || isEmpty(arg.servers) || isEmpty(arg.topic)) {
            System.out.println("please use -topic -group -servers");
            System.exit(1);
        }

        Properties props = getProp(arg.servers, arg.group);


        for (int i = 1; i <= 3; i++) {
            ConsumerThread t = new ConsumerThread("thead" + i, props, arg.topic);
            t.start();

            consumerThreads.add(t);
        }

        Runtime.getRuntime().addShutdownHook(new ShutdownHook(consumerThreads));

        consumerThreads.get(0).join();
    }

    public static Properties getProp(String servers, String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", "idea_5");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
