package java8.lambdafunctioninterface;

@FunctionalInterface
interface Ex3 {
    public String say(String name);
}

public class Example3 {
    public static void main(String[] args) {
        Ex3 ex3 = (a) -> "Hello" + a;

        String b = ex3.say("lam");
        System.out.println(b);
    }
}
