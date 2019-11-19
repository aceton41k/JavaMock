package saushkin.tests;

import org.testng.annotations.Test;

public class JustTest {


    @Test
    public void mainTest() {
        Just just = new Just();
        just.age = 50;
        just.name = "Cool";
        Just.dbl = 237687879;
        System.out.println(just.age);

        Just just2 = new Just();
        System.out.println(just2.age);

//        System.out.println(just.getAge());
    }
}
