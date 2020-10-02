package java8.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex1 {
    public static void main(String[] args) {
        IntStream.range(2,4).forEach(value -> System.out.println(value));
        IntStream.of(1,2,5).forEach(value -> System.out.println(value));
        List<String> items = new ArrayList<>();
        items.add("Hello");
        items.add("Pham");
        items.add("Hoang");
        items.add("Lam");
        items.stream().forEach(item->System.out.println(item));
        String str = "Welcome,to,gpcoder";
        Pattern.compile(",").splitAsStream(str).forEach(System.out::print);
        List<Integer> data = Arrays.asList(2, 3, 5, 4, 6);

        long count = data.stream().filter(num -> num % 3 == 0).count();
        System.out.println("Count = " + count);
        List<String> len = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");
        boolean result = len.stream().anyMatch((s) -> s.equalsIgnoreCase("Java"));
        System.out.println(result);

        Stream<String> stream = Stream.of("Java", "C#", "C++", "PHP", "Javascript");
        List<String> languages;
        languages = stream.collect(Collectors.toList());
        languages.stream().forEach(lan->System.out.println(lan));
    }
}
