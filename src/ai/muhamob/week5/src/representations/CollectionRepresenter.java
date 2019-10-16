package ai.muhamob.week5.src.representations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CollectionRepresenter<R extends Representation> {
    private final R representer;
    private final ArrayRepresenter<R> arrayRepresenter;

    public CollectionRepresenter(R representer) {
        this.representer = representer;
        arrayRepresenter = new ArrayRepresenter<>(representer);
    }

    public String getRepresentation(Object o, int depth) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder repr = new StringBuilder("");

        Method toArray = o.getClass().getMethod("toArray");
        Object collectionAsAnArray = toArray.invoke(o);
        repr.append(arrayRepresenter.getRepresentation(collectionAsAnArray, depth));

        return repr.toString();
    }
}
