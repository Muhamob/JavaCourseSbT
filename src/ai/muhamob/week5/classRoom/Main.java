package ai.muhamob.week5.classRoom;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Map<Integer, Integer> delegator = LoggerProxy.compute(new HashMap<Integer, Integer>());
//        delegator.put(1, 1);

        Calculator slowCalculatorLogger = LoggerProxy.compute(new SlowCalculator());
        Calculator fastCalculatorLogger = LoggerProxy.compute(new FastCalculator());
        System.out.println("result is " + slowCalculatorLogger.calc(1, 2));
        System.out.println("result is " + fastCalculatorLogger.calc(1, 2));
    }
}