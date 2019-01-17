package java8study;

/**
 * Created by firstsword on 2018/12/29.
 */

//自定义函数接口
@FunctionalInterface
public interface ConsumerInterface<T> {

    void accept(T t);
}
