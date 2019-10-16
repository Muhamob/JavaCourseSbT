package ai.muhamob.week5.src;

import ai.muhamob.week5.src.representations.JSONRepresentation;
import ai.muhamob.week5.src.representations.Representation;
import ai.muhamob.week5.src.representations.XMLRepresentation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Object o = new Node();
        Representation representation = new XMLRepresentation();
        Serializer serializer = new Serializer(representation);
        System.out.println(serializer.getRepr(o));
    }
}


class Node {
    public final String name = "adaad";
    public final Integer integer = 1;
    public final char c = 'c';
    public final int i = 0;
    public final Integer[] arr = {1, 2, 3, 4};
    public final Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5, 6 );
    public final Collection<String> stringCollection = Arrays.asList("a", "b", "c");
}

