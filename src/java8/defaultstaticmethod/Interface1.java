package java8.defaultstaticmethod;
@FunctionalInterface
public interface Interface1 {
    void hello();

    default void hi(String name) {
        System.out.println("Hi " + name);
    }

    static void run(){
        System.out.println("Im run");
    }
}
