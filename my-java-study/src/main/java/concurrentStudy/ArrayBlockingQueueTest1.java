package concurrentStudy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by firstsword on 2019/2/18.
 */
public class ArrayBlockingQueueTest1 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }, "thread-consumer").start();


        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                try {
                    queue.put("item" + i);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "thread-producer").start();
        System.out.println("...");
    }
}
