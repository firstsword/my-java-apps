package threadstudy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by firstsword on 2019/2/12.
 */
public class VolatileDemo1 {
    //https://www.cnblogs.com/chengxiao/p/6528109.html

    //volatile保证了共享变量在内存中的可见性

    //加了volatile修饰变量之后，当修改变量时，JMM会把该工作线程对应的本地内存中的变量
    //强制刷新到主内存中去，让其他工作线程的local内存中的该变量的缓存失效


    public static volatile int num = 0;
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);
    public static void main(String []args) throws InterruptedException {
        //开启30个线程进行累加操作
        for(int i=0;i<30;i++){
            new Thread(){
                public void run(){
                    for(int j=0;j<10000;j++){
                        num++;//自加操作
                        //并非原子性操作，是个复合操作(1.读取 2.加一 3.赋值)

                        //针对num++这类复合类的操作，
                        //可以使用java并发包中的原子操作类原子操作类是通过循环CAS的方式来保证其原子性的
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(num);
    }
}

//它主要有两个特性：\
// 1.是保证共享变量对所有线程的可见性；
// 2.是禁止指令重排序优化。
// 同时需要注意的是，volatile对于单个的共享变量的读/写具有原子性，
// 但是像num++这种复合操作，volatile无法保证其原子性，
// 当然文中也提出了解决方案，就是使用并发包中的原子操作类，
// 通过循环CAS地方式来保证num++操作的原子性。关于原子操作类，会在后续的文章进行介绍。
