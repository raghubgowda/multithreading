package com.raghu;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author RaghuBGowda
 */
public class AtomicIntegerExample{
    public static void main(String[] args){
        Worker worker = new Worker();
        worker.doWork();
    }
}

class Worker{
    private Logger logger = Logger.getGlobal();
    private AtomicInteger atomicCounter = new AtomicInteger(0);
    private long normalCounter = 0L;

    void doWork(){
        Thread t1 = new Thread(() -> {
            for(int i = 0; i<10000; i++){
                normalCounter++;
                atomicCounter.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i<10000; i++){
                normalCounter++;
                atomicCounter.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }
        catch(Exception ex){
            //Keep calm and continue
        }
        logger.info("Counters after both the threads are finished: NormalCounter - "+normalCounter+" & AtomicCounter - "+atomicCounter);
    }
}
