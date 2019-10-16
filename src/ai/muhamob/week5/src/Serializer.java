package ai.muhamob.week5.src;

import ai.muhamob.week5.src.representations.ArrayRepresenter;
import ai.muhamob.week5.src.representations.CollectionRepresenter;
import ai.muhamob.week5.src.representations.Representation;
import ai.muhamob.week5.src.utils.WrapperTypes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

public class Serializer<R extends Representation> {
    private final ArrayRepresenter<R> arrayRepresenter;
    private final CollectionRepresenter<R> collectionRepresenter;

    public Serializer(R representor) throws IllegalAccessException, InstantiationException {
        this.arrayRepresenter = new ArrayRepresenter<>(representor);
        this.collectionRepresenter = new CollectionRepresenter<>(representor);
    }

    public String getRepr(Object o) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        return getRepr(o, 0);
    }

    private String getRepr(Object o, int depth) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        StringBuilder repr = new StringBuilder("");
        Class<?> clazz = o.getClass();

        if (WrapperTypes.getWrapperTypes().contains(clazz)) {
            repr.append("\"").append(o.toString()).append(", \n");
        } else if (clazz.isArray()) {
            repr.append(arrayRepresenter.getRepresentation(o, depth));
        } else if (Arrays.asList(clazz.getInterfaces()).contains(Collection.class)) {
            repr.append(collectionRepresenter.getRepresentation(o, depth));
        } else {
            repr.append("{ \n");
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
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
}
