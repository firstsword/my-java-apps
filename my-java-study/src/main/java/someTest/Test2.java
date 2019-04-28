package someTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test2 {
    @Test
    public void t1() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a1");
        map.put("2", "a2");


        Map<Object, Object> map2 = new HashMap<>();
        map2.put("2", "a2");
        map2.put("1", "a1");

        boolean b = map.equals(map2);
        System.out.println(b);
    }
}
