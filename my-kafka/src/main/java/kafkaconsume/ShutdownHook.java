package kafkaconsume;

import java.util.List;

/**
 * Created by firstsword on 2019/3/14.
 */
public class ShutdownHook extends Thread {
    private final List<ConsumerThread> consumerThreads;

    public ShutdownHook(List<ConsumerThread> consumerThreads) {
        this.consumerThreads = consumerThreads;
    }

    @Override
    public void run() {
        try {
            System.out.println("shutdown");
            for (ConsumerThread t : consumerThreads) {
                t.shutdown();
            }
        } catch (Exception e) {
        }
    }
}
