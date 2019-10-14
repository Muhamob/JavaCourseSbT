package ai.muhamob.week5.classRoom;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {
    public static Map<String, Object> getAllFieldValue(Object o) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Class<?> superclass = o.getClass();

        while (superclass != null) {
            for (Field f : superclass.getDeclaredFields()) {
                f.setAccessible(true);
                Object val = f.get(o);
                map.put(f.getName(), val);
            }

            superclass = superclass.getSuperclass();
        }

        return map;
    }
}
