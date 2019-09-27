package ai.muhamob.week3.src;

import java.util.HashMap;

public class Main {
    private static HashMapArray<String, Integer> hashMapArray;

    public static void main(String[] args) {
        hashMapArray = new HashMapArray<>(10);

        hashMapArray.put("first", 1);
        hashMapArray.put("second", 2);
        hashMapArray.put("third", 3);
        hashMapArray.put("fourth", 4);
        hashMapArray.put("fives", 5);

        String elem = "fourth";

        if (hashMapArray.get(elem) instanceof Integer) {
            System.out.println("Right");
            System.out.println(hashMapArray.get(elem));
        } else {
            System.out.println("smth wrong");
        }
    }
}
