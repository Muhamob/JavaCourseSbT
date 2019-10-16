package ai.muhamob.week5.src.representations;

public class JSONRepresentation implements Representation {
    @Override
    public String representLine(String name, String value, int depth) {
        return "\t".repeat(depth) + "\"" + name + "\" : " + "\"" + value + "\"";
    }

    @Override
    public String representItem(String value, int depth) {
        return "\t".repeat(depth) + "\"" + value + "\"";
    }

    @Override
    public String getOpenSymbol(String name) {
        return "[ \n";
    }

    public String getOpenSymbol() {
        return getOpenSymbol(null);
    }

    @Override
    public String getCloseSymbol(String name, int depth) {
        return "\t".repeat(depth) + "], \n";
    }

    public String getCloseSymbol(int depth) {
        return getCloseSymbol(null, depth);
    }

}
