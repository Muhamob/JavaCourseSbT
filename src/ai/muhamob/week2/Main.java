package ai.muhamob.week2;

import ai.muhamob.week2.parser.Parser;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args) throws IOException {
        // Either you want to use file or std.in streams comment proper lines
        FileInputStream stream = new FileInputStream("./src/ai/muhamob/week2/test.txt");
        InputStreamReader streamReader = new InputStreamReader(stream);

//        InputStreamReader streamReader = new InputStreamReader(System.in);

        Parser parser = new Parser("switch");

        parser.readInput(streamReader);
    }
}
