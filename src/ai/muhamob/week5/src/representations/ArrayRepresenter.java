package ai.muhamob.week5.src.representations;

import java.lang.reflect.Array;

public class ArrayRepresenter<R extends Representation> {

    private final R representer;

    public ArrayRepresenter(R representer) {
        this.representer = representer;
    }

    public String getRepresentation(Object o, int depth) {
        StringBuilder repr = new StringBuilder(representer.getOpenSymbol(null));

        for (int i=0; i<Array.getLength(o); i++) {
            repr.append(representer.representItem(Array.get(o, i).toString(), depth+1));
            if (i != Array.getLength(o)-1) {
                repr.append(", \n");
            } else {
                repr.append("\n");
            }
        }

        repr.append(representer.getCloseSymbol(null, depth));
        return repr.toString();
    }
}
