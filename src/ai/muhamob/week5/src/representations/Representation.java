package ai.muhamob.week5.src.representations;

public interface Representation {
    public String representLine(String name, String value, int depth);
    public String representItem(String value, int depth);
    public String getOpenSymbol(String name);
    public String getCloseSymbol(String name, int depth);
}
