package concurrentStudy;

import java.time.Instant;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by firstsword on 2019/2/17.
 */
public class ConcurrentLinkedQueueTest2 {
    private static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {

        String empty1 = tickets.poll();
        if (empty1 == null) System.out.println("无票");

        String empty2 = tickets.poll();
        if (empty2 == null) System.out.println("无票");

    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        int threadNum = 11;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch c = new CountDownLatch(threadNum);

        long start = Instant.now().getEpochSecond();

        new Thread(()->{
            for (int i = 1; i <= 1000000; i++) {
                tickets.offer("ticket" + i);
            }

            c.countDown();

        }, "thread-producer").start();


        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(() -> {
                while (!tickets.isEmpty()) {
                    String ticket = tickets.poll();
                /*System.out.println(Thread.currentThread().getName() + ":" +
                        ticket);*/

                    /*if (ticket == null) break;
                    else {
                        count.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + ":" +
                                ticket);
                    }*/

                    if (ticket != null) {
                        count.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + ":" +
                                ticket);
                    }

                }

                c.countDown();

            }, "thread" + i);

            t.start();
        }



        c.await();

        long end = Instant.now().getEpochSecond();

        System.out.println(end - start);
        System.out.println("总票数"+count.get());
    }


}
