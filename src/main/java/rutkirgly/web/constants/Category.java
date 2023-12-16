package rutkirgly.web.constants;

public enum Category {
    Car(0),
    Buss(1),
    Truck(2),
    Motorcycle(3);

    final int switchCode;

    Category(int switchCode){
        this.switchCode = switchCode;
    }
    public static Category fromCode(int code) {
        for (Category category : values()) {
            if (category.switchCode == code) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
