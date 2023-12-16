package rutkirgly.web.constants;

public enum Engine {
    Gasoline(0),
    Diesel(1),
    Electric(2),
    Hybrid(4);
    final int switchCode;

    Engine(int switchCode){
        this.switchCode = switchCode;
    }
    public static Engine fromCode(int code) {
        for (Engine engine : values()) {
            if (engine.switchCode == code) {
                return engine;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
