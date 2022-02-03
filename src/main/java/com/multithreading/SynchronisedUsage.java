package com.multithreading;

public class SynchronisedUsage extends Thread {
    //Static synchronization to prevent multiple locks with object lock at method level
    synchronized static void test(int num) {
        for (int i = 0; i < 6; i++)
            System.out.println(num + i);
    }

    //Synchronized method to have object lock at method level
    synchronized void test1(int num) {
        for (int i = 0; i < 6; i++)
            System.out.println(num + i);

    }

    // Synchronized block to have specific object lock at specific place only inside method
    void test2(int num) {
        System.out.println(Thread.currentThread().getName());
        synchronized (this) {
            for (int i = 0; i < 6; i++)
                System.out.println(num + i);
        }
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        SynchronisedUsage ob = new SynchronisedUsage();
        //Synchronized method to have 2 thread  working in synchronous manner
        Thread t1 = new Thread(() -> ob.test1(100));
        Thread t2 = new Thread(() -> ob.test1(200));
        t1.start();
        t2.start();

        //Synchronized block to have 2 thread  working in synchronous manner inside method
        Thread t3 = new Thread(() -> ob.test2(100));
        Thread t4 = new Thread(() -> ob.test2(200));
        t3.start();
        t4.start();


        //Static synchronization example with 2 threads
        Thread t5 = new Thread(() -> SynchronisedUsage.test(100));
        Thread t6 = new Thread(() -> SynchronisedUsage.test(200));
        t5.start();
        t6.start();

    }
}
