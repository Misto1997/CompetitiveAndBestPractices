package com.java8;

import java.util.Arrays;
import java.util.List;

//It gives a Boolean value as a result for a specified argument.
public class PredicateTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Smith", "Samueal", "Catley", "Sie");
        names.stream().filter(str -> str.startsWith("S")).forEach(str -> System.out.println(str));

        //custom predicate with method reference
        names.stream().filter(PredicateTest::test).forEach(System.out::println);
    }

    public static boolean test(String s) {
        return s.length() > 5;
    }
}
