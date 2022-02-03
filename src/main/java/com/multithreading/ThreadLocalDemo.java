package com.multithreading;

public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal();
        threadLocal.set(1);
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());

    }
}
