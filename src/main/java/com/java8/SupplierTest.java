package com.java8;

import java.util.function.Supplier;

//It represents a function which does not take in any argument but produces a value of type T.
public class SupplierTest {

    public static void main(String[] args) {
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println(randomValue.get());
    }
}
