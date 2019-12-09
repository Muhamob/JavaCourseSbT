package ai.muhamob.threads.src.threads;

public class Semaphore {
    private final int maxThreads;
    private int currentThreads;
    private final Object lock = new Object();

    public Semaphore(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public void lock() {
        synchronized (lock) {
            System.out.println("Try to lock by" + Thread.currentThread().getName());

            if (currentThreads < maxThreads) {
                currentThreads += 1;
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unlock() {
        synchronized (lock) {
            currentThreads -= 1;
            lock.notify();
            System.out.println("Thread " + Thread.currentThread().getName() + " unlocked");
        }
    }
}
