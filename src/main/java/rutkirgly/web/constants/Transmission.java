package rutkirgly.web.constants;

public enum Transmission {
    Manual(0),
    Automatic(1);


    final int switchCode;

    Transmission(int switchCode){
        this.switchCode = switchCode;
    }
    public static Transmission fromCode(int code) {
        for (Transmission transmission : values()) {
            if (transmission.switchCode == code) {
                return transmission;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
