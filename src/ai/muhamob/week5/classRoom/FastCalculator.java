package ai.muhamob.week5.classRoom;

public class FastCalculator implements Calculator {
    @Override
    public int calc(int a, int b) throws InterruptedException {
        return a+b;
    }
}
