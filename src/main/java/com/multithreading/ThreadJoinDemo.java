package com.multithreading;

//Thread join example it will complete the current Thread first before starting another thread
public class ThreadJoinDemo extends Thread {

    @Override
    public void run() {
        try {
            test1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1);
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadJoinDemo();
        Thread t2 = new ThreadJoinDemo();
        Thread t3 = new ThreadJoinDemo();

        t1.start();
            t1.join();
        t2.start();
            t2.join();
        t3.start();
    }
}
