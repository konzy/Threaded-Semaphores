package com.company;

import java.util.concurrent.Semaphore;

import static com.company.ThreadTest.MAX_WRITERS;

/**
 * Created by konzy on 4/28/2017.
 */
public class Reader extends Thread {
    private Semaphore rMutex;
    private Semaphore wMutex;
    private int threadNumber;

    public Reader(Semaphore rMutex, Semaphore wMutex, int threadNumber) {
        this.rMutex = rMutex;
        this.wMutex = wMutex;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        while(!rMutex.tryAcquire());
        while(wMutex.availablePermits() < MAX_WRITERS); // no writers in critical section
        // critical section

        System.out.println("reader " + threadNumber + " has entered critical section");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("reader " + threadNumber + " has left critical section");
        rMutex.release();

    }
}
