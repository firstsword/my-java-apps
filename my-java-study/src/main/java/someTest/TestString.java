package someTest;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by firstsword on 2019/3/7.
 */
public class TestString {
    @Test
    public void t1() {
        String s1 = null;
        String s2 = "";

        System.out.println(Objects.isNull(s1));
        System.out.println(Objects.isNull(s2));

        System.out.println(s1.isEmpty());

    }
}
