package ai.muhamob.week5.src.representations;

import java.lang.reflect.InvocationTargetException;

public interface Representation {
    public String getStringForPrimitiveObj(String name, Object o, int depth);
    public String getStringForArrayObj(String name, Object o, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    public String getOpenSymbol();
    public String getCloseSymbol();
    public String getEOLSymbol();
    public String getClassRepresentation(String name, Object o, int depth) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    public String getStringForMapObj(String name, Object o, int depth) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
