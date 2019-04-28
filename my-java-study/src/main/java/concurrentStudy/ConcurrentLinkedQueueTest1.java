package concurrentStudy;

import sun.java2d.SurfaceDataProxy;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by firstsword on 2019/2/17.
 */
public class ConcurrentLinkedQueueTest1 {
    private static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 1; i <= 1000000; i++) {
            tickets.add("ticket" + i);
        }
    }

    public static void main(String[] args) throws Exception {
        int threadNum = 10;

        long start = Instant.now().getEpochSecond();

        CountDownLatch c = new CountDownLatch(threadNum);

        AtomicInteger count = new AtomicInteger(0);

        String finalTicket = "ticket" + tickets.size();

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                while (true) {
                    //synchronized (tickets) {
                        if (tickets.size() <= 0) break;

                        String curTicket = tickets.remove(0);

                        if (curTicket != null) {
                            count.incrementAndGet();
                            System.out.println(Thread.currentThread().getName() + ":" + curTicket);
                        }
                    //}
                }
                c.countDown();
            }, "thread" + i).start();
        }


        c.await();

        long end = Instant.now().getEpochSecond();
        System.out.println(end - start);
        System.out.println("总数：" + count.get());


    }
}
