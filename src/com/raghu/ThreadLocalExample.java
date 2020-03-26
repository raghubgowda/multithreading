package com.raghu;

public class ThreadLocalExample {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}

class MyRunnable implements Runnable {

    //private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private Counter counter = new Counter();

    @Override
    public void run() {
        //threadLocal.set( (int) (Math.random() * 100D) );
        counter.set((int) (Math.random() * 100D));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        //System.out.println(threadLocal.get());
        System.out.println(counter.get());
    }
}

class Counter{

    private int count = 0;

    public int get(){
        return count;
    }

    public void set(int _count){
        count = _count;
    }

}