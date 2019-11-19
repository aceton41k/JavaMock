//import static spark.Spark.*;

public class RectangleTest {

    public static void main (String [] args) {
        Rectangle rect = new Rectangle(1,2,3,4);
//        System.out.println(rect.height);

        Rectangle rect2 = new Rectangle(rect);
//        System.out.println(rect2.top);

        Rectangle rect3 = new Rectangle(2,5,7);
//        System.out.println(rect3.height);

        Rectangle square = new Rectangle(8, 9);
        System.out.println(square.height);


    }


}