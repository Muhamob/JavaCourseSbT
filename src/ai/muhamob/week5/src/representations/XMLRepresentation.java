package ai.muhamob.week5.src.representations;

public class XMLRepresentation implements Representation {
    @Override
    public String representLine(String name, String value, int depth) {
        return "\t".repeat(depth) + getOpenSymbol(name) + value + getCloseSymbol(name);
    }

    @Override
    public String representItem(String value, int depth) {
        return "\t".repeat(depth) + getOpenSymbol("arrayItem") +
                value + getCloseSymbol("arrayItem");
    }

    @Override
    public String getOpenSymbol(String name) {
        return "<" + name + ">";
    }

    @Override
    public String getCloseSymbol(String name, int depth) {
        return "\t".repeat(depth) + getCloseSymbol(name) + "\n";
    }

    private String getCloseSymbol(String name) {
        return "</" + name + ">";
    }
}
