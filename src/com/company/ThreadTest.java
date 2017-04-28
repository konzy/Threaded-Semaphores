package com.company;

import java.util.concurrent.Semaphore;

/**
 * Brian Konzman
 * Daniel Slone
 */
public class ThreadTest {

    public static final int MAX_WRITERS = 1;
    public static final int MAX_READERS = Integer.MAX_VALUE;
    private final Semaphore rMutex = new Semaphore(MAX_READERS, true);
    private final Semaphore wMutex = new Semaphore(MAX_WRITERS, true);

    void runTests() {
        Reader reader1 = new Reader(rMutex, wMutex, 1);
        Reader reader2 = new Reader(rMutex, wMutex, 2);
        Reader reader3 = new Reader(rMutex, wMutex, 3);
        Writer writer = new Writer(rMutex, wMutex, 1);

        reader1.start();
        writer.start();
        try {
            Thread.sleep(110);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reader2.start();
        reader3.start();

    }
}
