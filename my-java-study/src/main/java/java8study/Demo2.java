package java8study;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by firstsword on 2018/12/29.
 */
public class Demo2 {

    //参考：http://www.cnblogs.com/CarpenterLee/p/6729368.html

/*
接口名	    Java8新加入的方法
Collection: removeIf() spliterator() stream() parallelStream() forEach()

List: replaceAll() sort()


Map: getOrDefault() forEach() replaceAll() putIfAbsent() remove() replace()
computeIfAbsent() computeIfPresent() compute() merge()

*/

    List<String> list = new ArrayList<>(Arrays.asList("aa", "bb", "cc"));

    @Test
    public void t1() {
        list.forEach(s -> {
            System.out.println(s);
        });
    }

    @Test
    public void t2() {
        System.out.println(list); //[aa, bb, cc]
        list.removeIf(x -> x.startsWith("a"));
        System.out.println(list); //[bb, cc]

        List<String> list2 = list.stream()
                .filter(x -> x.startsWith("b"))
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(list2);

    }

    @Test
    public void t3() {
        System.out.println(list); //[aa, bb, cc]
        list.replaceAll(x -> x.toUpperCase());
        System.out.println(list); //[AA, BB, CC]
    }

    @Test
    public void t4() {
        List<String> list = new ArrayList<>(Arrays.asList("aaa", "b", "cc"));
        System.out.println(list);// [aaa, b, cc]
        list.sort((s1, s2) -> s1.length() - s2.length());
        System.out.println(list);// [b, cc, aaa]

        list.sort((s1, s2) -> {
            System.out.println("test");
            return s2.length() - s1.length();
        });

        System.out.println(list);
    }

    @Test
    public void t5() {
        //Spliterator是一个可分割迭代器(splitable iterator)

        //Spliterator既可以像Iterator那样逐个迭代，也可以批量迭代。批量迭代可以降低迭代的开销。

        //Spliterator是可拆分的，一个Spliterator可以通过调用Spliterator<T> trySplit()方法来尝试分成两个。
        // 一个是this，另一个是新返回的那个，这两个迭代器代表的元素没有重叠。

        //jdk1.8发布后，对于并行处理的能力大大增强，Spliterator就是为了并行遍历元素而设计的一个迭代器，
        // jdk1.8中的集合框架中的数据结构都默认实现了spliterator，
        // 后面我们也会结合ArrayList中的spliterator()一起解析。

        //list.spliterator()
    }

    //stream()和parallelStream()分别返回该容器的Stream视图表示，
    // 不同之处在于parallelStream()返回并行的Stream。Stream是Java函数式编程的核心类


    public Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "aa");
        map.put(2, "bb");
        map.put(3, "cc");

        return map;
    }

    @Test
    public void t6() {
        getMap().forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    @Test
    public void t7() {
        String s = getMap().getOrDefault(5, "dd");
        System.out.println(s);
    }

    @Test
    public void t8() {
        //作用是只有在不存在key值的映射或映射值为null时，才将value指定的值放入到Map中，否则不对Map做更改
        //该方法将条件判断和赋值合二为一，使用起来更加方便
        Map<Integer, String> map = getMap();
        System.out.println(map);
        map.putIfAbsent(3, "dd");
        System.out.println(map);
    }

    @Test
    public void t9() {
        //我们都知道Map中有一个remove(Object key)方法，来根据指定key值删除Map中的映射关系；
        //Java8新增了remove(Object key, Object value)方法，
        // 只有在当前Map中key正好映射到value时才删除该映射，否则什么也不做
        Map<Integer, String> map = getMap();
        System.out.println(map);
        map.remove(1, "aaa");
        System.out.println(map);
    }

    @Test
    public void t10() {
        //在Java7及以前，要想替换Map中的映射关系可通过put(K key, V value)方法实现，
        //该方法总是会用新值替换原来的值．为了更精确的控制替换行为，Java8在Map中加入了两个replace()方法，分别如下：

        //replace(K key, V value)，只有在当前Map中key的映射存在时才用value去替换原来的值，否则什么也不做．
        //replace(K key, V oldValue, V newValue)，只有在当前Map中key的映射存在且等于oldValue时才用newValue去替换原来的值，否则什么也不做
        Map<Integer, String> map = getMap();
        System.out.println(map);

        map.replace(1, "aaa");
        System.out.println(map);

        map.replace(111, "111");
        System.out.println(map);

        map.replace(1, "aa", "2a");
        System.out.println(map);

        map.replace(1, "aaa", "3a");
        System.out.println(map);
    }

    @Test
    public void t11() {
        //map.replaceAll()作用是对Map中的每个映射执行function指定的操作，并用function的执行结果替换原来的value，
        Map<Integer, String> map = getMap();
        System.out.println(map);

        map.replaceAll((k, v) -> {
            System.out.println("replace");
            return v.toUpperCase();
        });

        System.out.println(map);
    }

    @Test
    public void t12() {
        //该方法签名为merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)，作用是：

        //如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上；
        //否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射．
        Map<Integer, String> map = getMap();
        System.out.println(map);//{1=aa, 2=bb, 3=cc}

        //merge()方法虽然语义有些复杂，但该方法的用方式很明确
        //一个比较常见的场景是将新的错误信息拼接到原来的信息上，比如：
        map.merge(1, "new", (oldValue, newValue) -> {
            System.out.println(oldValue);
            System.out.println(newValue);
            return oldValue + newValue;
        });

        System.out.println(map);//{1=aanew, 2=bb, 3=cc}
    }

    @Test
    public void t13() {
        //该方法签名为compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)，
        // 作用是把remappingFunction的计算结果关联到key上，
        // 如果计算结果为null，则在Map中删除key的映射．
        Map<Integer, String> map = getMap();
        System.out.println(map);

        map.compute(1, (k, v) -> v.concat("-later"));

        System.out.println(map);

        //map.computeIfAbsent(key, k -> new Value(f(k)));

        //map.computeIfPresent(1, (k, v) -> v);

    }


}
