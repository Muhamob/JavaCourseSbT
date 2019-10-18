package ai.muhamob.week5.src.representations;

import ai.muhamob.week5.src.Serializer;
import ai.muhamob.week5.src.utils.WrapperTypes;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class JSONRepresentation implements Representation {

    private String openSymbol = "{";
    private String closeSymbol = "}";
    private String EOLSymbol = ",";

    @Override
    public String getEOLSymbol() {
        return EOLSymbol;
    }

    @Override
    public String getOpenSymbol() {
        return openSymbol;
    }

    @Override
    public String getCloseSymbol() {
        return closeSymbol;
    }

    @Override
    public String getStringForPrimitiveObj(String name, Object o) {
        String value = o.toString();
        String repr = "\"" + name + "\" : ";
        if (o.getClass().getSuperclass() == Number.class) {
            return repr + value;
        }
        return repr + "\"" + value + "\"";
    }

    public String getStringForPrimitiveObj(String name, String value) {
        return "\"" + name + "\" : \"" + value + "\"";
    }

    @Override
    public String getStringForArrayObj(String name, Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        StringBuilder out = new StringBuilder();
        out.append("\"").append(name).append("\" : [");


        for (int i=0; i<Array.getLength(o); i++) {
            Object obj = Array.get(o, i);
            if (WrapperTypes.getWrapperTypes().contains(obj.getClass())) {

                if (obj.getClass().getSuperclass() == Number.class) {
                    out.append(obj.toString());
                } else {
                    out.append("\"").append(obj.toString()).append("\"");
                }
                if (i != Array.getLength(o) - 1) {
                    out.append(",");
                }
            } else {
                out.append(getClassRepresentation(obj));
            }
        }

        out.append("],");
        return out.toString();
    }

    private String getClassRepresentation(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Serializer<JSONRepresentation> serializer = new Serializer<>(new JSONRepresentation());
        return serializer.serialize(o);
    }

    @Override
    public String getClassRepresentation(String name, Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return "\"" + name + "\" : " +
                getClassRepresentation(o);
    }

    @Override
    public String getStringForMapObj(String name, Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map map = (Map) o;

        StringBuilder repr = new StringBuilder();
        repr.append("\"" + name + "\" : ");

        for (Object key : map.entrySet()) {
            Object value = map.get(key);
            String keyRepresentation = getClassRepresentation(key);
            String valueRepresentation = getClassRepresentation(value);
            repr.append(getStringForPrimitiveObj(keyRepresentation, valueRepresentation));
        }

        return repr.toString();
    }
}
