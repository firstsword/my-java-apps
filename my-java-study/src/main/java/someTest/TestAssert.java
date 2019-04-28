package someTest;

import org.junit.Test;

/**
 * Created by firstsword on 2019/2/14.
 */
public class TestAssert {
    @Test
    public void t1() {
        assert 1 < 0 : "1 must gt 0";
        System.out.println("hello");
    }

    @Test
    public void t2() {
        assert 1 > 0 : "1 must gt 0";
        System.out.println("hello");
    }
}
