package com.company;

import java.util.concurrent.Semaphore;

import static com.company.ThreadTest.MAX_READERS;


/**
 * Created by konzy on 4/28/2017.
 */

public class Writer extends Thread {
    private Semaphore rMutex;
    private Semaphore wMutex;
    private int threadNumber;

    public Writer(Semaphore rMutex, Semaphore wMutex, int threadNumber) {
        this.rMutex = rMutex;
        this.wMutex = wMutex;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        while(rMutex.availablePermits() < MAX_READERS); // no readers in critical section
        while(!wMutex.tryAcquire());
        // critical section

        System.out.println("writer " + threadNumber + " has entered critical section");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("writer " + threadNumber + " has left critical section");
        wMutex.release();

    }
}
