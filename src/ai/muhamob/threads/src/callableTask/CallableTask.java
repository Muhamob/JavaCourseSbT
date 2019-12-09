package ai.muhamob.threads.src.callableTask;

import java.util.concurrent.Callable;

public class CallableTask<T> {
    private T result;
    private Callable<? extends T> callable;
    private RuntimeException computeException;
    private volatile boolean computed = false;
    private volatile boolean failedWithException = false;

    public CallableTask(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        synchronized (this) {
            if (computed) {
                return result;
            }
            if (failedWithException) {
                throw computeException;
            }
            try {
                result = callable.call();
                computed = true;
                this.notifyAll();
                return result;
            } catch (Exception e) {
                computeException = new RuntimeException("Compute exception", e);
                failedWithException = true;
            }
        }

        return result;
    }
}
