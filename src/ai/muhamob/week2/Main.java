package ai.muhamob.week2;

import ai.muhamob.week2.parser.Parser;

import java.io.*;

public class Main {

    private static String tradeSearchMethod = "enum";

    public static void main (String[] args) throws IOException {
        // Either you want to use file or std.in streams comment proper lines
        InputStreamReader streamReader = getStreamReader(args);

        Parser parser = new Parser(tradeSearchMethod);
        parser.readInput(streamReader);
    }

    private static InputStreamReader getStreamReader(String[] args) throws IOException {
        // Return proper StreamReader depending on args

        if (args.length == 0) {
            return new InputStreamReader(System.in);
        } else if (args.length == 1) {
            FileInputStream stream = new FileInputStream(args[0]);
            return new InputStreamReader(stream);
        } else {
            // this handles cases with incorrect arguments
            return new InputStreamReader(System.in);
        }
    }
}
