package java8study;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by firstsword on 2018/12/29.
 */

public class MainTest {

    @Test
    public void t1() {
        ConsumerInterface<String> consumer = x -> System.out.println(x);

        consumer.accept("abc");
    }
}
