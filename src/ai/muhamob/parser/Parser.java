package ai.muhamob.parser;

import ai.muhamob.core.Trade;
import ai.muhamob.core.TradeType;

import java.io.*;

import static ai.muhamob.core.TradeType.FX_SPOT;

public class Parser {

    public void readInput(InputStreamReader streamReader) throws IOException {
//        InputStreamReader inputStream = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);

        // parse one trade
        Trade trade = parseOneTrade(reader);

        // do smth with trade, e.g. print it
        System.out.println(trade.getPrice());
    }

    private Trade parseOneTrade(BufferedReader reader) throws IOException {
        String type;
        double price;

        // read first line
        String s = reader.readLine();
        // read second line
        s = reader.readLine();
        type = s.split("=")[1];
        // read third line
        s = reader.readLine();
        price = Double.parseDouble(s.split("=")[1]);

        TradeType enumTradeType = TradeType.valueOf(type);
        Trade trade = enumTradeType.createTrade(price);

        return trade;
    }
}
