package com.java8;

import java.util.ArrayList;
import java.util.List;

//A Consumer is a functional interface that accepts a single input and returns no output.
public class ConsumerTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(a -> {
            System.out.println(a);
            System.out.println(a + 2);
        });
        //method reference
        list.forEach(System.out::println);

        //custom Consumer with method reference
        list.forEach(ConsumerTest::test);
    }

    public static void test(Integer a) {
        System.out.println(a);
    }

}
