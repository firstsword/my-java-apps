package java8study.lambdaDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by firstsword on 2019/2/22.
 */
public class Base1 {

    static List<String> names = new ArrayList<>();

    static {
        names.add("scala");
        names.add("java");
        names.add("golang");
        names.add("c++");
    }

    @Test
    public void t1() {
        BinaryOperator<Long> add = (Long x, Long y) -> x + y;

        long count = names.stream()
                .filter(x -> x.startsWith("j"))
                .count();

        System.out.println(count);

        List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        System.out.println(collected);

    }

    @Test
    public void t2() {
        List<String> list = Stream.of("a", "b", "c")
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testFlatMap() {
        List<Integer> list = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(nums -> nums.stream())
                .collect(Collectors.toList());
        System.out.println(list);
    }


}
//函数式编程核心是:在思考问题时，使用不可变值和函数，函数对一个值进行处理，映射成另一个值。
//我们关心的是如何写出好代码，而不是符合函数式编程风格的代码。

//本书将重点放在函数式编程的实用性上，包括可以被大多数程序员理解和使用的技术，帮助他们写出易读、易维护的代码。

//Lambda表达式：一种紧凑的、传递行为的方式


//匿名内部类实现了该方法。在例 2-1 中该方法所执行的只是输出一条信息，表明按钮已被点击
//这实际上是一个代码即数据的例子——我们给按钮传递了一个代表某种行为的对象。

//设计匿名内部类的目的，就是为了方便 Java 程序员将代码作为数据传递。

//button.addActionListener(event -> System.out.println("button clicked"));

//如果你曾使用过匿名内部类，也许遇到过这样的情况:需要引用它所在方法里的变量。这时，需要将变量声明为final

//如果你试图给该变量多次赋值，然后在 Lambda 表达式中引用它，编译器就会报错

//函数接口是只有一个抽象方法的接口，用作 Lambda 表达式的类型


//像filter这样只描述 Stream，最终不产生新集合的方法叫作惰性求值方法;
//而像 count 这样 最终会从 Stream 产生值的方法叫作及早求值方法

//判断一个操作是惰性求值还是及早求值很简单:只需看它的返回值
// 如果返回值是 Stream， 那么是惰性求值;如果返回值是另一个值或为空，那么就是及早求值

//如果有一个函数可以将一种类型的值转换成另外一种类型，map 操作就可以 使用该函数，将一个流中的值转换成一个新的流












