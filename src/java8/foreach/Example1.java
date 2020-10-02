package java8.foreach;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example1 {
    public static void main(String[] args) {
        // foreach() map
        Map<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(1, "Java");
        hmap.put(2, "Javascript");
        hmap.put(3, "PHP");
        hmap.put(4, "C#");
        hmap.put(5, "C++");

        // forEach to iterate and display each key and value pair of HashMap
        hmap.forEach((key, value) -> System.out.println(key + " - " + value));

        //foreach() list
        List<String> languages = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");

        System.out.println("Iterating by passing lambda expression: ");
        languages.forEach(lang -> System.out.println(lang));

        System.out.println("Iterating by passing method reference: ");
        languages.forEach(System.out::println);

        //forEachOrdered

        System.out.println("Iterating by passing lambda expression: ");
        languages.stream().forEachOrdered(lang -> System.out.println(lang));

        System.out.println("Iterating by passing method reference: ");
        languages.stream().forEachOrdered(System.out::println);
    }
}
