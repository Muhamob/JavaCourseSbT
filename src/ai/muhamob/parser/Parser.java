package ai.muhamob.parser;

import ai.muhamob.core.*;

import java.io.*;

public class Parser {

    private String tradeSearch;

    public Parser(String tradeSearch) {
        // trade search determines which method of matching string with trade class will be used
        // if tradeSearch is switch, then switch construction will be used
        // else if enum, then enum will be used
        // default enum
        this.tradeSearch = tradeSearch;
    }

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

        if (this.tradeSearch.equals("switch")) {
            return createTradeSwitch(type, price);
        } else if (this.tradeSearch.equals("enum")) {
            return createTradeEnum(type, price);
        } else {
            return createTradeEnum(type, price);
        }
    }

    private Trade createTradeSwitch(String type, double price) {
        Trade trade;

        switch (type) {
            case "FX_SPOT":
                trade = new FxSpot(price);
                break;
            case "BOND":
                trade = new Bond(price);
                break;
            case "COMMODITY_SWAO":
                trade = new CommoditySpot(price);
                break;
            case "IR_SWAP":
                trade = new IrSwap(price);
                break;
            default:
                trade = new FxSpot(price);
                break;
        }

        return trade;
    }

    private Trade createTradeEnum(String type, double price) {
        // This is the second way to get proper class
        TradeType enumTradeType = TradeType.valueOf(type);
        return enumTradeType.createTrade(price);
    }

}
