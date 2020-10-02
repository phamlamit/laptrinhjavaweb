package java8.lambdafunctioninterface;

@FunctionalInterface
interface Ex2 {
    public String say();
}

public class Example2 {
    public static void main(String[] args) {
        Ex2 ex2 = () -> "Im Lam";
        System.out.println(ex2.say());

    }
}
