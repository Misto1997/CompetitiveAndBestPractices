package com.multithreading;

import java.util.concurrent.*;

//Callable
class Test implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1;
    }
}

//Runnable
class Test1 implements Runnable {


    @Override
    public void run() {
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Test ob = new Test();
        Test1 ob1 = new Test1();
        ExecutorService service = Executors.newSingleThreadExecutor();

        //Runnable returning null
        Future future = service.submit(ob1);
        System.out.println(future.get());

        //callable returning value
        Future<Integer> future1 = service.submit(ob);
        System.out.println(future1.get().intValue());
        service.shutdown();
    }
}
