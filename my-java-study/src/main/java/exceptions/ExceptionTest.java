package exceptions;

import java.util.Arrays;
import java.util.List;

public class ExceptionTest {
    public static void main(String[] args) {
        /*try {
            new JustTest().t();
        } catch (MyException e) {
            System.out.println("my exception...");
            System.out.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("exception...");
            e.printStackTrace();
        }*/

        /*String[] s = null;
        for (String tmp : s) {
            System.out.println(tmp);
        }*/
        List<String> list = Arrays.asList("java", "scala", "python");
        list.forEach(x -> System.out.println(x));
        list.stream()
                .forEach(x -> System.out.println(x));
    }
}
