public class OutputTest {
    {
        System.out.println("2");
    }

    OutputTest() {
        System.out.println("3");
    }

    public static void main(String[] args) {
        System.out.println("4");
        OutputTest output = new OutputTest();
        System.out.println("5");
    }

    static {
        System.out.println("1");
    }
}
