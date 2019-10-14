package ai.muhamob.week5.classRoom;

public class SlowCalculator implements Calculator {
    @Override
    public int calc(int a, int b) throws InterruptedException {
        Thread.sleep(1_000);
        return a+b;
    }
}
