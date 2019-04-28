package someTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by firstsword on 2019/2/13.
 */
public class TestTemp {
    @Test
    public void t1() {
        Map<String, String> m = new HashMap<>();
        m.put("1", "A");
        m.put("2", "B");
        m.put("3", "C");

        Map<String, String> m2 = m;

        m2.put("4", "D");

        System.out.println(m);
        System.out.println(m2);

        System.out.println("--------");

        m.remove("1");

        System.out.println(m);
        System.out.println(m2.toString());
    }

    @Test
    public void t2() {
        Map<String, Object> m = new HashMap<>();
        m.put("1", "A");
        m.put("2", m);

        System.out.println(m);
    }

    @Test
    public void t3() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> list2 = list.subList(1, 3);

        System.out.println(list);
        System.out.println(list2);
    }

    @Test
    public void t4() {
        assert 1 < 0 : "1 must gt 0";
        System.out.println("hello");
    }
}

