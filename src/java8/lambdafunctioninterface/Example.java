package java8.lambdafunctioninterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Example {
    public static void main(String[] args) {

        List<String> languages = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");
        Collections.sort(languages, (l1, l2) -> l1.compareTo(l2));
        languages.forEach(lang -> System.out.println(lang));
    }

}

