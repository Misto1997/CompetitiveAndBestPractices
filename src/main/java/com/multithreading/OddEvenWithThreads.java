package com.multithreading;

//wait(): it is a non-static method that causes the current thread to wait and go
// to sleep until some other threads call the notify () or notifyAll() method for the
// object’s monitor (lock). It simply releases the lock and is mostly used for inter-thread
// communication. It is defined in the object class, and should only be called from a
// synchronized context.

//sleep(): it is a static method that pauses or stops the execution of the current thread
// for some specified period. It doesn’t release the lock while waiting and is mostly used
// to introduce pause on execution. It is defined in thread class, and no need to call from
// a synchronized context.

// Odd Even Number with 2 different Thread using wait and notify
public class OddEvenWithThreads {
    int number = 1;

    public synchronized void printOddNumber() {
        while (number < 10) {
            if (number % 2 == 0)
                try {
                    wait();
                } catch (InterruptedException e) {
                }

            System.out.println(number);
            number++;
            notify();
        }
    }

    public synchronized void printEvenNumber() {
        while (number < 10) {
            if (number % 2 != 0)
                try {
                    wait();
                } catch (InterruptedException e) {
                }

            System.out.println(number);
            number++;
            notify();
        }
    }

    public static void main(String[] args) {
        OddEvenWithThreads ob = new OddEvenWithThreads();
        Thread oddThread = new Thread(ob::printOddNumber);
        Thread evenThread = new Thread(ob::printEvenNumber);
        oddThread.start();
        evenThread.start();
    }

}
