package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //intermediate functions
        //map takes functions that takes one input and returns another one
        List square = list.stream().map(x -> x * x).collect(Collectors.toList());

        //filter takes predicate that takes one input and returns boolean
        List result = list.stream().filter(s -> s > 2).collect(Collectors.toList());

        //sorted is used to sort the stream
        List result1 = list.stream().sorted().collect(Collectors.toList());


        //terminal functions
        //collect is used to return the result of intermediate function
        List square1 = list.stream().map(x -> x * x).collect(Collectors.toList());

        // foreach is used to iterate through collection
        list.stream().map(x -> x * x).forEach(y -> System.out.println(y));

        //reduce takes binaryOperator and return a single output
        int even = list.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);

    }
}
