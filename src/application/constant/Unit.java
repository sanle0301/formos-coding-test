package application.constant;

public enum Unit {

    MILLILIT("ml"), GRAM("g");

    private String symbol;

    Unit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
