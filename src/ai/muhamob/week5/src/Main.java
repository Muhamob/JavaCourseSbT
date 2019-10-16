package ai.muhamob.week5.src;

import ai.muhamob.week5.src.representations.JSON;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        SimpleObject o = new SimpleObject();

        System.out.println(JSON.getRepr(o));
        String a = "asad";

    }
}

