package ai.muhamob.threads.test.barriers;

import ai.muhamob.threads.src.barriers.Barrier;
import org.junit.jupiter.api.Test;

class BarrierTest {
    @Test
    void testInit() {
        SimpleTask task = new SimpleTask();

        System.out.println("Before threads count was " + task.getCount());
        for (int i=0; i<11; i++) {
            new Thread(() -> {
                try {
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("After thread count is " + task.getCount());
    }
}


class SimpleTask {
    private int count = 0;
    private Barrier barrier = new Barrier(10);

    void run() throws InterruptedException {
        System.out.println("Running from " + Thread.currentThread().getName());
        count += 1;
        barrier.await();
        System.out.println("After await " + Thread.currentThread().getName());
    }

    int getCount() {
        return count;
    }
}