package ai.muhamob.week5.src;

import ai.muhamob.week5.src.representations.Representation;
import ai.muhamob.week5.src.utils.WrapperTypes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class Serializer<R extends Representation> {
    private final R representation;

    public Serializer(R representation) {
        this.representation = representation;
    }

    public String serialize(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getFields();

        StringBuilder repr = new StringBuilder(representation.getOpenSymbol());

        for (int i=0; i<fields.length; i++) {
            Field field = fields[i];
            Object fieldObj = field.get(o);
            String repr_ = "";
            String fieldName = field.getName();

            if (WrapperTypes.getWrapperTypes().contains(fieldObj.getClass())) {
                repr_ = serializePrimitive(fieldName, fieldObj);

                repr.append(repr_);
                if (i != fields.length-1) {
                    repr.append(representation.getEOLSymbol());
                }
            } else if (fieldObj.getClass().isArray()) {
                repr.append(serializeArray(fieldName, fieldObj));
            } else if (List.class.isAssignableFrom(fieldObj.getClass())) {
                repr.append(serializeList(fieldName, fieldObj));
            } else if (Map.class.isAssignableFrom(fieldObj.getClass())) {
                repr.append(serializeMap(fieldName, fieldObj));
            } else {
                repr.append(serializeClass(fieldName, fieldObj));
            }
        }

        repr.append(representation.getCloseSymbol()).append(representation.getEOLSymbol());

        return repr.toString();
    }

    private String serializeMap(String name, Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return representation.getStringForMapObj(name, o);
    }

    private String serializeClass(String name, Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return representation.getClassRepresentation(name, o);
    }

    private String serializeList(String name, Object fieldObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method toArray = fieldObj.getClass().getMethod("toArray");
        toArray.setAccessible(true);
        Object collectionAsAnArray = toArray.invoke(fieldObj);

        return representation.getStringForArrayObj(name, collectionAsAnArray);
    }

    private String serializeArray(String name, Object fieldObj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return representation.getStringForArrayObj(name, fieldObj);
    }

    private String serializePrimitive(String name, Object fieldObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return representation.getStringForPrimitiveObj(name, fieldObj);
    }
}
