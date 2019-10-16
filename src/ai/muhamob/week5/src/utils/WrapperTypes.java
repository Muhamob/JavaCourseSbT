package ai.muhamob.week5.src.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WrapperTypes {

    private static Set<Class<?>> ret = new HashSet<Class<?>>(Arrays.asList(
            Boolean.class,
            Character.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Void.class,
            String.class
    ));

    public static Set<Class<?>> getWrapperTypes() {
        return ret;
    }
}
