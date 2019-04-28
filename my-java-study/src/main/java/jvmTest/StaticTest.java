package jvmTest;

/**
 * Created by firstsword on 2019/3/17.
 */
public class StaticTest {
    //实例初始化不一定要在类初始化结束之后才开始初始化。

    //类的生命周期是：加载->验证->准备->解析->初始化->使用->卸载


    //只有在准备阶段和初始化阶段才会涉及类变量的初始化和赋值，因此只针对这两个阶段进行分析；

    //类的准备阶段需要做是为类变量分配内存并设置默认值，因此类变量st为null、b为0；

    //需要注意的是如果类变量是final，编译时javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将变量设置为指定的值。
    //如果这里这么定义：static final int b=112，那么在准备阶段b的值就是112，而不再是0了。


    public static void main(String[] args) {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;
}
//类的准备阶段需要做是为类变量分配内存并设置默认值，因此类变量st为null、b为0；
//类的初始化阶段需要做的是执行类构造器。

//对象的初始化是先初始化成员变量，再执行构造方法


//其实并没有提前，你要知道java记录初始化与否的时机。看一个简化的代码，把关键问题解释清楚：

/*

public class Test {
    public static void main(String[] args) {
        func();
    }
    static Test st = new Test();
    static void func(){}
}
根据上面的代码，有以下步骤：

1.首先在执行此段代码时，首先由main方法的调用触发静态初始化。
2.在初始化Test 类的静态部分时，遇到st这个成员。
3.但凑巧这个变量引用的是本类的实例。
4.那么问题来了，此时静态初始化过程还没完成就要初始化实例部分了。是这样么？
5.从人的角度是的。但从java的角度，一旦开始初始化静态部分，无论是否完成，后续都不会再重新触发静态初始化流程了。
6.因此在实例化st变量时，实际上是把实例初始化嵌入到了静态初始化流程中，并且在楼主的问题中，嵌入到了静态初始化的起始位置。
这就导致了实例初始化完全至于静态初始化之前。这也是导致a有值b没值的原因。
*/
