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
    public String getStringForPrimitiveObj(String name, Object o, int depth) {
        String value = o.toString();
        return "\t".repeat(depth) + "\"" + name + "\" : \"" + value + "\"";
    }

    public String getStringForPrimitiveObj(String name, String value, int depth) {
        return "\t".repeat(depth) + "\"" + name + "\" : \"" + value + "\"";
    }

    @Override
    public String getStringForArrayObj(String name, Object o,int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        StringBuilder out = new StringBuilder();
        out.append("\t".repeat(depth)).append("\"").append(name).append("\" : [ \n");


        for (int i=0; i<Array.getLength(o); i++) {
            Object obj = Array.get(o, i);
            out.append("\t".repeat(depth+1));
            if (WrapperTypes.getWrapperTypes().contains(obj.getClass())) {
                out.append("\"").append(obj.toString());
                if (i != Array.getLength(o) - 1) {
                    out.append("\", \n");
                } else {
                    out.append("\" \n");
                }
            } else {
                out.append(getClassRepresentation(obj, depth+1));
            }
        }

        out.append("\t".repeat(depth)).append("], \n");
        return out.toString();
    }

    private String getClassRepresentation(Object o, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Serializer<JSONRepresentation> serializer = new Serializer<>(new JSONRepresentation());
        return serializer.serialize(o, depth);
    }

    @Override
    public String getClassRepresentation(String name, Object o, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return "\t".repeat(depth) + "\"" + name + "\" : " +
                getClassRepresentation(o, depth);
    }

    @Override
    public String getStringForMapObj(String name, Object o, int depth) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map map = (Map) o;

        StringBuilder repr = new StringBuilder();
        repr.append("\t".repeat(depth) + "\"" + name + "\" : ");

        for (Object key : map.entrySet()) {
            Object value = map.get(key);
            String keyRepresentation = getClassRepresentation(key, depth);
            String valueRepresentation = getClassRepresentation(value, depth);
            repr.append(getStringForPrimitiveObj(keyRepresentation, valueRepresentation, depth+1));
        }

        return repr.toString();
    }
}
