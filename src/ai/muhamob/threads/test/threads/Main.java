package ai.muhamob.threads.test.threads;

import ai.muhamob.threads.src.threads.Semaphore;

public class Main {
    private Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        Main main = new Main();

        for (int i=0; i<6; i++) {
            new Thread(main::run).start();
        }
    }

    private void run() {
        semaphore.lock();
        try {
            doRun();
        } finally {
            semaphore.unlock();
        }
    }

    private void doRun() {
        System.out.println("thread id in semaphore " + Thread.currentThread().getId());
    }
}
