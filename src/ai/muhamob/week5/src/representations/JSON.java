package ai.muhamob.week5.src.representations;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class JSON {

    public static String getRepr(Object o) throws NoSuchFieldException, IllegalAccessException {
        return getRepr(o, 0);
    }

    private static String getRepr(Object o, int depth) throws IllegalAccessException, NoSuchFieldException {
        StringBuilder repr = new StringBuilder("");
        Class<?> clazz = o.getClass();

        if (getWrapperTypes().contains(clazz)) {
            return "\"" + o.toString() + "\", \n";
        } else {
            repr.append("{ \n");
            for (Field field : clazz.getFields()) {
                String repr_ = "\t".repeat(depth+1) + "\""
                        + field.getName() + "\" : ";

                Object subFieldObj = field.get(o);
                repr_ += getRepr(subFieldObj, depth+1);
                repr.append(repr_);
            }
            repr.append("\t".repeat(depth)).append("}, \n");
        }

        return repr.toString();
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
}
