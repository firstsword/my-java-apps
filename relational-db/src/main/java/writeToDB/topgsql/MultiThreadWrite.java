package writeToDB.topgsql;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadWrite {

    public static void main(String[] args) throws Exception {
        int from = 19500000;
        int to = 19501000;

        int threadNum = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);

        int step = (to - from + 1) / threadNum;
        long start = System.currentTimeMillis();

        for (int i = from; i <= to; i += (step + 1)) {
            int j = i + step;
            if (j > to) j = to;
            System.out.println(i + ", " + j);
            executor.submit(new InsertCallable(i, j));
        }

        executor.shutdown();

        if (executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
            long duration = (System.currentTimeMillis() - start) / 1000;

            long speed = 0;
            if (duration > 0) {
                speed = (to - from + 1) / duration;
            }

            System.out.println("time: " + duration);
            System.out.println("speed: " + speed + " data per second");

            ConnUtils.close();
        }
    }
}
