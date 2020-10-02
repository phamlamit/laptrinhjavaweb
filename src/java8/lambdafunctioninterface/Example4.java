package java8.lambdafunctioninterface;

public class Example4 {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Run 1");
            }
        };
        Runnable r2 = ()->System.out.println("Run 2");
        r1.run();
        r2.run();
    }
}
