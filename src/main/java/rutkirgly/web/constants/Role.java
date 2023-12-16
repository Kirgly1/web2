package rutkirgly.web.constants;

public enum Role {
    User(0),
    Admin(1);


    final int switchCode;

    Role(int switchCode){
        this.switchCode = switchCode;
    }
    public static Role fromCode(int code) {
        for (Role role : values()) {
            if (role.switchCode == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
