package java8study;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * java8 Stream介绍
 * Created by firstsword on 2018/12/30.
 */
public class Demo3 {

    //http://www.cnblogs.com/CarpenterLee/p/6729368.html

    //1.代码简洁，函数式编程写出的代码简洁且意图明确，使用stream接口让你从此告别for循环。
    //2.多核友好，Java函数式编程使得编写并行程序从未如此简单，你需要的全部就是调用一下parallel()方法

    //调用Collection.stream()或者Collection.parallelStream()方法
    //调用Arrays.stream(T[] array)方法


    //虽然大部分情况下stream是容器调用Collection.stream()方法得到的，但stream和collections有以下不同：

    //1.无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
    //2.为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
    //3.惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
    //4.可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。


    /*

    对stream的操作分为为两类，中间操作(intermediate operations)和结束操作(terminal operations)

    二者特点是：
    1. 中间操作总是会惰式执行，调用中间操作只会生成一个标记了该操作的新stream，仅此而已。
    2. 结束操作会触发实际计算，计算发生时会把所有中间操作积攒的操作以pipeline的方式执行，这样可以减少迭代次数。
    计算完成之后stream就会失效。

    操作类型	接口方法
    中间操作	concat() distinct() filter() flatMap() limit() map() peek()
    skip() sorted() parallel() sequential() unordered()
    结束操作	allMatch() anyMatch() collect() count() findAny() findFirst()
    forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()

    */

    //区分中间操作和结束操作最简单的方法，就是看方法的返回值，返回值为stream的大都是中间操作，否则是结束操作。

    //stream跟函数接口关系非常紧密，没有函数接口stream就无法工作。
    // 回顾一下：函数接口是指内部只有一个抽象方法的接口。
    // 通常函数接口出现的地方都可以使用Lambda表达式，所以不必记忆函数接口的名字。

    @Test
    public void t1() {

        //函数原型为Stream<T> distinct()，作用是返回一个去除重复元素之后的Stream
        Stream<String> stream = Stream.of("Hello", "java", "Golang", "Hello");
        stream.distinct()
                .forEach(System.out::print);//HellojavaGolang
    }

    @Test
    public void t2() {

        //sorted()
        //排序函数有两个，一个是用自然顺序排序，函数原型为Stream<T>　sorted()
        // 一个是使用自定义比较器排序，函数原型为Stream<T>　sorted(Comparator<? super T> comparator)

        //方式1
        Stream<String> stream = Stream.of("Hello", "java", "Golang", "Hello");

        stream.sorted()
                .forEach(System.out::print);//GolangHelloHellojava

        System.out.println();

        //方式2
        Stream<String> stream2 = Stream.of("Hello", "java", "Golang");


        stream2.sorted((x, y) -> x.length() - y.length())
                .forEach(System.out::print);//java Hello Golang

        //注意 java8中 用::把一个方法转成了参数(方法参数) 类名::方法名

        //jdk8中使用了::的用法。就是把方法当做参数传到stream内部，
        // 使stream的每个元素都传入到该方法里面执行一下
        // 双冒号运算就是Java中的[方法引用],[方法引用]的格式是 类名::方法名
    }
}
