import aa.Person;
import aa.Student;
import aa.Teacher;

/**
 * Created by firstsword on 2019/1/21.
 */
public class mytest {

    public static void main(String[] args) {
        Student s = new Student();
        s.print();
        Teacher t = new Teacher();

        Person.b = false;


        t.print();
    }
}
