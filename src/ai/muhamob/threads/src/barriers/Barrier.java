package ai.muhamob.threads.src.barriers;

public class Barrier {
    private final int maxThreads;
    private int currentThreads = 0;

    public Barrier(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public void await() {
        synchronized (this) {
            currentThreads += 1;
            if (currentThreads <= maxThreads) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                this.notifyAll();
            }
        }
    }
}
