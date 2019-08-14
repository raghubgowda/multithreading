package com.raghu;

import java.util.logging.Logger;

/**
 * @author RaghuBGowda
 * To see the difference, remove volatile keyword from the mainCounter
 */
public class VolatileExample {
    private static final Logger logger = Logger.getGlobal();

    private static volatile int mainCounter = 0;

    public static void main(String[] args) {
        new Consumer().start();
        new Producer().start();
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            int localCounter = mainCounter;
            while ( localCounter < 5){
                if( localCounter != mainCounter){
                    logger.info("mainCounter value is changed to : "+ mainCounter);
                    localCounter = mainCounter;
                }
            }
        }
    }

    static class Producer extends Thread{
        @Override
        public void run() {

            int localCounter = mainCounter;
            while (mainCounter < 5){
                logger.info("Incrementing mainCounter to "+localCounter+1);
                mainCounter = ++localCounter;
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {
                    logger.warning("Exception in Producer thread.  Exception is "+ex);
                }
            }
        }
    }
}
