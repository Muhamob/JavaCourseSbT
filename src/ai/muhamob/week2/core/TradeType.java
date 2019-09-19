package ai.muhamob.week2.core;

public enum TradeType {
    FX_SPOT {
        @Override
        public FxSpot createTrade(double price) {
            return new FxSpot(price);
        }
    },

    BOND {
        @Override
        public Bond createTrade(double price) {
            return new Bond(price);
        }
    },

    COMMODITY_SPOT {
        @Override
        public CommoditySpot createTrade(double price) {
            return new CommoditySpot(price);
        }
    },

    IR_SWAP {
        @Override
        public IrSwap createTrade(double price) {
            return new IrSwap(price);
        }
    };

    public abstract Trade createTrade(double price);
}