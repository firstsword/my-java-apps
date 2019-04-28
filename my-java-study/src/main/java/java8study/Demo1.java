package java8study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 函数式编程简介
 * Created by firstsword on 2018/12/23.
 */
public class Demo1 {

    //参考：https://www.cnblogs.com/snowInPluto/p/5981400.html

    //什么是函数式编程？简单的回答：一切都是数学函数。要么是函数参数，要什么是函数返回值。
    //它把计算过程当做是数学函数的求值，而避免更改状态和可变数据。

    //函数式编程语言里没有 for/next 循环，因为这些逻辑意味着有状态的改变。
    //相替代的是，这种循环逻辑在函数式编程语言里是通过递归、把函数当成参数传递的方式实现的


    /*
    比如说，这个
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.out.println("button clicked");
        }
    });

    可以用lambda表达式来写：

    button.addActionListener(event -> System.out.println("button clicked"));

    */

    @Test
    public void t1() {
        new Thread(() -> System.out.println(Thread.currentThread().getName()),
                "thread name")
                .start(); // thread name

        System.out.println(Thread.currentThread().getName()); // main
    }


/*    Java 中 Lambda 表达式一共有五种基本形式，具体如下：

    // 用空括号 () 表示没有参数
    Runnable noArguments = () -> System.out.println("Hello World");

    // 表达式包含且只包含一个参数,可省略参数的括号
    ActionListener oneArgument = event -> System.out.println("button clicked");

    // 代码块用 {}
    Runnable multiStatement = () -> {
        System.out.print("Hello");
        System.out.println(" World");
    };

    // 多个函数参数的例子
    BinaryOperator<Long> add = (x, y) -> x + y;

    BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

 */

    @Test
    public void t2() {
        BinaryOperator<Integer> add = (x, y) -> x + y;

    }
    //记住一点很重要，Lambda 表达式都可以扩写为原始的“匿名类”形式。
    //所以当你觉得这个 Lambda 表达式很复杂不容易理解的时候，不妨把它扩写为“匿名类”形式来看。


    /*
         如果你以前使用过匿名内部类，也许遇到过这样的问题。
         当你需要匿名内部类所在方法里的变量，必须把该变量声明为 final。如下例子所示

         final String name = getUserName();
         button.addActionListener(new ActionListener() {
             public void actionPerformed (ActionEvent event){
                 System.out.println("hi " + name);
             }
         });

         Java 8放松了这一限制，可以不必再把变量声明为 final，
         但其实该变量实际上仍然是 final 的。虽然无需将变量声明为 final，
         但在 Lambda 表达式中，也无法用作非终态变量。
         如果坚持用作非终态变量（即改变变量的值），编译器就会报错。
 */
    String outerName = "outerName";

    @Test
    public void t3() {

        new Thread(() -> System.out.println(outerName)
                , "thread name")
                .start();

    }


   /*

   public interface ActionListener extends EventListener {


        public void actionPerformed(ActionEvent e);

    }

    ActionListener 只有一个抽象方法：actionPerformed，
    被用来表示行为:接受一个参数，返回空。记住，由于 actionPerformed 定义在一个接口里，
    因此 abstract 关键字不是必需的。该接口也继承自一个不具有任何方法的父接口：EventListener。

    我们把这种接口就叫做  "函数接口"。

    */


    //接口       	参数	返回类型	描述
    //Predicate<T>	T	boolean	用于判别一个对象。比如求一个人是否为男性
    @Test
    public void t4() {
        Predicate<Object> isStudent = o -> o == null;

    }

    //Consumer<T>	T	void  用于接收一个对象进行处理但没有返回，比如接收一个人并打印他的名字
    @Test
    public void t5() {
        Consumer<Object> consume = o -> System.out.println(o);
    }

    //Supplier<T>	None	T	提供一个对象
    @Test
    public void t6() {
        Supplier<Integer> supply = () -> Integer.valueOf(10);
    }

    //Function<T, R>	T	R	转换一个对象为不同类型的对象
    @Test
    public void t7() {
        Function<String, Integer> strToint = x -> Integer.valueOf(x);
    }

    //UnaryOperator<T>	T	T	接收对象并返回同类型的对象
    @Test
    public void t8() {
        UnaryOperator<Object> f = x -> 100;

        System.out.println(f.apply(1));
        System.out.println(f.apply("test"));
        System.out.println(f.apply(new Object()));
    }

    //BinaryOperator<T>	(T, T)	T	接收两个同类型的对象，并返回一个原类型对象
    @Test
    public void t9() {
        BinaryOperator<Object> f = (x, y) -> x;
    }

    //其中 Cosumer 与 Supplier 对应，一个是消费者，一个是提供者。

    //Predicate 用于判断对象是否符合某个条件，经常被用来过滤对象。

    //Function 是将一个对象转换为另一个对象，比如说要装箱或者拆箱某个对象。

    //UnaryOperator 接收和返回同类型对象，一般用于对对象修改属性。BinaryOperator 则可以理解为合并对象。


    //集合处理
    //所有继承自 Collection 的接口都可以转换为 Stream
    @Test
    public void t10() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        list.stream()
                .filter(x -> x > 3)
                .map(x -> x * x)
                .forEach(x -> System.out.print(x + ","));

        //现在链式调用方法算是一个主流，这样写也更利于阅读和理解编写者的意图，一步方法做一件事。
    }


    // Stream 的方法分为两类。一类叫惰性求值，一类叫及早求值。
    // 判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。
    // 如果返回值是 Stream，那么是惰性求值。
    // 其实可以这么理解，如果调用惰性求值方法，Stream 只是记录下了这个惰性求值方法的过程，
    // 并没有去计算，等到调用及早求值方法后，就连同前面的一系列惰性求值方法顺序进行计算，返回结果。

    // 通用形式为:
    //Stream.惰性求值.惰性求值. ... .惰性求值.

    @Test
    public void t11() {
        //collect(toList()) 方法由 Stream 里的值生成一个列表，是一个及早求值操作。
        // 可以理解为 Stream 向 Collection 的转换
        List<String> list = Stream.of("ab", "ac", "bc").collect(Collectors.toList());
        System.out.println(list);//[ab, ac, bc]
    }

    @Test
    public void t12() {
        //如果有一个函数可以将一种类型的值转换成另外一种类型，
        // map 操作就可以使用该函数，将一个流中的值转换成一个新的流
        // map 方法就是接受的一个 Function 的匿名函数类，进行的转换
        List<String> list = Stream.of("ab", "ac", "bc")
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(list);//[AB, AC, BC]
    }

    @Test
    public void t13() {
        //遍历数据并检查其中的元素时，可尝试使用 Stream 中提供的新方法 filter
        //filter 方法就是接受的一个 Predicate 的匿名函数类，判断对象是否符合条件，符合条件的才保留下来
        List<String> list = Stream.of("ab", "ac", "bc")
                .filter(x -> x.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println(list);// [ab, ac]
    }

    @Test
    public void t14() {
        //flatMap 方法可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
        //flatMap 最常用的操作就是合并多个 Collection
        List<String> list = Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d"))
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        System.out.println(list);// [a, b, c, d]

    }

    @Test
    public void t15() {
        //Stream 上常用的操作之一是求最大值和最小值。Stream API 中的 max 和 min 操作足以解决这一问题
        List<Integer> list = Arrays.asList(1, 2, 5, 3, 6);

        int max = list.stream()
                .max(Integer::compareTo)
                .get();

        int min = list.stream()
                .min(Integer::compareTo)
                .get();

        System.out.println(max); //6
        System.out.println(min); //1

        //max 和 min 方法返回的是一个 Optional 对象（对了，和 Google Guava 里的 Optional 对象是一样的）。
        // Optional 对象封装的就是实际的值，可能为空，所以保险起见，可以先用 isPresent() 方法判断一下。
        // Optional 的引入就是为了解决方法返回 null 的问题

        //Integer::compareTo 也是属于 Java 8 引入的新特性，叫做 方法引用（Method References）。
        // 在这边，其实就是 (int1, int2) -> int1.compareTo(int2) 的简写，可以自己查阅了解，这里不再多做赘述
    }

    @Test
    public void t16() {
        //reduce 操作可以实现从一组值中生成一个值。
        // 在上述例子中用到的 count、min 和 max 方法,因为常用而被纳入标准库中。
        // 事实上，这些方法都是 reduce 操作

        int res = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, e) -> acc + e);
        System.out.println(res);

        //注意 reduce 的第一个参数，这是一个初始值。0 + 1 + 2 + 3 + 4 = 10

        //  https://docs.oracle.com/javase/8/docs/api/
    }

    @Test
    public void t17() {
        //Stream 的并行化也是 Java 8 的一大亮点。
        // 数据并行化是指将数据分成块，为每块数据分配单独的处理单元。
        // 这样可以充分利用多核 CPU 的优势
        int sumSize = Stream.of("first", "sword", "wind")
                .parallel()
                .map(s -> s.length())
                .reduce(Integer::sum)
                .get();
        System.out.println(sumSize);

        //并行化操作流只需改变一个方法调用。
        // 如果已经有一个 Stream 对象，调用它的 parallel() 方法就能让其拥有并行操作的能力。
        // 如果想从一个集合类创建一个流，调用 parallelStream() 就能立即获得一个拥有并行能力的流

        //如果你去计算这段代码所花的时间，很可能比不加上 parallel() 方法花的时间更长。
        // 这是因为数据并行化会先对数据进行分块，然后对每块数据开辟线程进行运算，这些地方会花费额外的时间。
        // 并行化操作只有在 数据规模比较大 或者 数据的处理时间比较长 的时候才能体现出有事，
        // 所以并不是每个地方都需要让数据并行化，应该具体问题具体分析。

    }

    //Collectors （收集器）的内容太多，有兴趣的可以自己研究
    //http://my.oschina.net/joshuashaw/blog/487322
    //https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html

    @Test
    public void t18() {
        List<Integer> list = Arrays.asList(1, 2, 3, 0, 6);
        List<Integer> sortedList = list.stream()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());

        System.out.println(sortedList);

        //另外一个尚未提及的关于集合类的内容是流中的元素以何种顺序排列。
        // 一些集合类型中的元素是按顺序排列的，比如 List；而另一些则是无序的，比如 HashSet。
        // 增加了流操作后，顺序问题变得更加复杂。

        //总之记住。如果集合本身就是无序的，由此生成的流也是无序的。
        // 一些中间操作会产生顺序，比如对值做映射时，映射后的值是有序的，这种顺序就会保留 下来。
        // 如果进来的流是无序的，出去的流也是无序的。

        //如果我们需要对流中的数据进行排序，可以调用 sorted 方法：
    }


    //3.4.3 @FunctionalInterface
    //我们讨论过函数接口定义的标准，但未提及 @FunctionalInterface 注释。
    // 事实上，每个用作函数接口的接口都应该添加这个注释。

    //但 Java 中有一些接口，虽然只含一个方法，但并不是为了使用 Lambda 表达式来实现的。
    // 比如，有些对象内部可能保存着某种状态，使用带有一个方法的接口可能纯属巧合。

    //该注释会强制 javac 检查一个接口是否符合函数接口的标准。
    // 如果该注释添加给一个枚举类型、类或另一个注释，或者接口包含不止一个抽象方法，javac 就会报错。
    // 重构代码时，使用它能很容易发现问题。

}
