package com.java8;

import java.util.Arrays;
import java.util.List;

//A Function interface is more of a generic one that takes one argument and produces a result.
public class FunctionTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Smith", "Gourav", "Heather", "John", "Catania");
        names.stream().map(str -> str.toUpperCase()).forEach(str -> System.out.println(str));

        //parallel processing
        names.parallelStream().map(str -> str.toUpperCase()).forEach(str -> System.out.println(str));

        //custom Function with method reference
        names.stream().map(FunctionTest::test).forEach(System.out::println);
    }

    public static Integer test(String s) {
        return s.length();
    }
}
