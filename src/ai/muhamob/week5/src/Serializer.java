package ai.muhamob.week5.src;

import ai.muhamob.week5.src.representations.Representation;
import ai.muhamob.week5.src.utils.WrapperTypes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

public class Serializer<R extends Representation> {
    private final R representation;

    public Serializer(R representation) {
        this.representation = representation;
    }

    public String serialize(Object o, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder repr = new StringBuilder(representation.getOpenSymbol() + "\n");

        for (int i=0; i<fields.length; i++) {
            Field field = fields[i];
            Object fieldObj = field.get(o);
            String repr_ = "";
            String fieldName = field.getName();

            if (WrapperTypes.getWrapperTypes().contains(fieldObj.getClass())) {
                repr_ = serializePrimitive(fieldName, fieldObj, depth+1);

                repr.append(repr_);
                if (i != fields.length-1) {
                    repr.append(representation.getEOLSymbol());
                }
                repr.append("\n");
            } else if (fieldObj.getClass().isArray()) {
                repr.append(serializeArray(fieldName, fieldObj, depth+1));
            } else if (Collection.class.isAssignableFrom(fieldObj.getClass())) {
                repr.append(serializeCollection(fieldName, fieldObj, depth+1));
            } else {
                repr.append(serializeClass(fieldName, fieldObj, depth+1));
            }
        }

        repr.append("\t".repeat(depth) + representation.getCloseSymbol() + representation.getEOLSymbol() + "\n");

        return repr.toString();
    }

    private String serializeClass(String name, Object o, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return representation.getClassRepresentation(name, o, depth);
    }

    private String serializeCollection(String name, Object fieldObj, int depth) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method toArray = fieldObj.getClass().getMethod("toArray");
        toArray.setAccessible(true);
        Object collectionAsAnArray = toArray.invoke(fieldObj);

        return representation.getStringForArrayObj(name, collectionAsAnArray, depth);
    }

    private String serializeArray(String name, Object fieldObj, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return representation.getStringForArrayObj(name, fieldObj, depth);
    }

    private String serializePrimitive(String name, Object fieldObj, int depth) {
        return representation.getStringForPrimitiveObj(name, fieldObj, depth);
    }
}
