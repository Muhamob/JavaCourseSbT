package ai.muhamob.week5.src;

import ai.muhamob.week5.src.representations.JSONRepresentation;
import ai.muhamob.week5.src.representations.Representation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object o = new Node();
        Representation representation = new JSONRepresentation();
        Serializer serializer = new Serializer(representation);
        System.out.println(serializer.serialize(o, 0));
    }
}


class Node {
    public final String name = "adaad";
    public final Integer integer = 1;
    public final char c = 'c';
    public final int i = 0;
    public final Integer[] arr = {1, 2, 3, 4};
    public final Collection<String> stringCollection = Arrays.asList("a", "b", "c");
    public final SimpleObject simpleObject = new SimpleObject();
    public final Collection<SimpleObject> nodeList = Arrays.asList(new SimpleObject(), new SimpleObject());
}

