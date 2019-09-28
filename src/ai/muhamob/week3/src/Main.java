package ai.muhamob.week3.src;

import java.util.HashMap;

// TODO: 1. Create tests !!!
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

        // check if remove works
        // should return null
        hashMapArray.remove(elem);
        System.out.println(hashMapArray.get(elem));

        // check if contains works
        // should return false and true
        System.out.println(hashMapArray.contains(elem));
        System.out.println(hashMapArray.contains("first"));
    }
}
