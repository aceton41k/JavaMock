public class Rectangle {
    public int width, left, top, height;

    public Rectangle(int height, int top, int left, int width) {
         this.width = width;
         this.height = height;
         this.top = top;
         this.left = left;
    }

    //Конструктор без параметров высота и ширина
    public Rectangle(int top, int left) {
        this.top = top;
        this.left = left;
    }


    //Конструктор без параметра высота (берется от ширины, т.к. это квадрат).
    public Rectangle(int top, int left, int width) {
        this.height = width;
        this.width = width;
        this.top = top;
        this.left = left;
    }

    //Конструктор, который принимает как параметр другой объект типа Rectangle

    public Rectangle(Rectangle rectangle) {
        this.height = rectangle.height;
        this.top = rectangle.top;
        this.left = rectangle.left;
        this.width = rectangle.width;

//        System.out.println(rectangle.height);

    }


}
