package argsStudy;

/**
 * Created by firstsword on 2019/2/13.
 */
public class Demo {

    public static void main(String[] args) throws Exception{
        Args arg = new Args();

        try {
            arg.parseArgs(args);
        } catch (Exception e) {
            throw e;
        }

        System.out.println(arg.option);
        System.out.println(arg.required);
    }

}
