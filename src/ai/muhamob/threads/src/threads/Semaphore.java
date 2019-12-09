package ai.muhamob.threads.src.threads;

public class Semaphore {
    private final int maxThreads;
    private int currentThreads;

    public Semaphore(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public void lock() {
        synchronized (this) {
            System.out.println("Try to lock by" + Thread.currentThread().getName());

            if (currentThreads < maxThreads) {
                currentThreads += 1;
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unlock() {
        synchronized (this) {
            currentThreads -= 1;
            this.notify();
            System.out.println("Thread " + Thread.currentThread().getName() + " unlocked");
        }
    }
}
