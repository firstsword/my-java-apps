package kafkaconsume;

import java.util.*;

/**
 * Created by firstsword on 2019/3/14.
 */
public class App2 {
    private static List<ConsumerThread2> consumerThreads = new ArrayList<>();


    public static void main(String[] args) throws Exception{
        Args arg = new Args();
        arg.parseArgs(args);

        if (isEmpty(arg.group) || isEmpty(arg.servers) || isEmpty(arg.topic)) {
            System.out.println("please use -topic -group -servers");
            System.exit(1);
        }

        Map<String, Object> props = getProp2(arg.servers, arg.group);


        for (int i = 1; i <= 1; i++) {
            ConsumerThread2 t = new ConsumerThread2("thead" + i, props, arg.topic);
            t.start();

            consumerThreads.add(t);
        }


        //Runtime.getRuntime().addShutdownHook(new ShutdownHook(consumerThreads));

        consumerThreads.get(0).join();
    }
    public static Map<String, Object> getProp2(String servers, String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", servers);
        //props.put("group.id", "idea_4");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
