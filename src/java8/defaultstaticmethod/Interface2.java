package java8.defaultstaticmethod;
@FunctionalInterface
public interface Interface2 extends Interface1{

    default void hi(String name) {
        System.out.println("Hello " + name);
    }
}
