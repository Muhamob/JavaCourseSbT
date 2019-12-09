package ai.muhamob.threads.test.callableTask;

import ai.muhamob.threads.src.callableTask.CallableTask;
import org.junit.jupiter.api.Test;

public class CallableTaskTest {
    @Test
    void task1() {
        CallableTask<String> t = new CallableTask<>(() -> {
            Thread.sleep(3000);
            //throw new Exception("Exception in thread " + Thread.currentThread().getId());
            return "some result of computing";
        });

        for (int i = 0; i < 20; ++i) {
            new Thread(() -> {
                    System.out.println("run thread " + Thread.currentThread().getId() + "...");
                    String result = t.get();
                    System.out.println("    in thread " + Thread.currentThread().getId() + " result = " + result);
                }).start();
        }
    }
}
