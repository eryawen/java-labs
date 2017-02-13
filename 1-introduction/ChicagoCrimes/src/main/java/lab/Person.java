package lab;

/**
 * Created by Monika_ on 2016-11-10.
 */
public class Person {
        Fullname fullname;

        Person(String fn) {
            fullname = new Fullname(fn);
        }




static class Fullname{
    String fn;
    Fullname(String fn) {
        fn = fn;
    }
}
}
