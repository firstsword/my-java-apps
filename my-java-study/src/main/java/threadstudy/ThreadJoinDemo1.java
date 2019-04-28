package threadstudy;

/**
 * Created by firstsword on 2019/2/12.
 */
public class ThreadJoinDemo1 {
    //https://www.cnblogs.com/huangzejun/p/7908898.html

    //当我们调用某个线程的这个方法时，这个方法会挂起调用线程，直到被调用线程结束执行，调用线程才会继续执行。

    /*// 父线程
    public class Parent extends Thread {
        public void run() {
            Child child = new Child();
            child.start();
            child.join();
            // ...
        }
    }

    // 子线程
    public class Child extends Thread {
        public void run() {
            // ...
        }
    }

上面代码展示了两个类：Parent（父线程类），Child（子线程类）。

在 Parent.run() 中，通过 Child child = new Child(); 新建 child 子线程（此时 child 处于 NEW 状态）；

然后再调用 child.start()（child 转换为 RUNNABLE 状态）；

再调用 child.join()。



在 Parent 调用 child.join() 后，child 子线程正常运行，Parent 父线程会等待 child 子线程结束后再继续运行

*/

//a. join() 和 join(long millis, int nanos) 最后都调用了 join(long millis)。
//b. 带参数的 join() 都是 synchronized method。
//c. join() 调用了 join(0)，从源码可以看到 join(0) 不断检查当前线程（join() 所属的线程实例，非调用线程）是否是 Active。
//d. join() 和 sleep() 一样，都可以被中断（被中断时，会抛出 InterrupptedException 异常）；
// 不同的是，join() 内部调用了 wait()，会出让锁，而 sleep() 会一直保持锁。


//1.Parent 调用 child.join()，child.join() 再调用 child.join(0)
// （此时 Parent 会获得 child 实例作为锁，其他线程可以进入 child.join() ，但不可以进入 child.join(0)， 因为无法获取锁）。
// child.join(0) 会不断地检查 child 线程是否是 Active。


//2.如果 child 线程是 Active，则循环调用 child.wait(0)
// （为了防止 Spurious wakeup, 需要将 wait(0) 放入 for 循环体中；
// 此时 Parent 会释放 child 实例锁，其他线程可以竞争锁并进入 child.join(0)。
// 我们可以得知，可以有多个线程等待某个线程执行完毕）。

//3.一旦 child 线程不为 Active （状态为 TERMINATED）,
// child.join(0) 会直接返回到 child.join(), child.join() 会直接返回到 Parent 父线程，
// Parent 父线程就可以继续运行下去了
}
