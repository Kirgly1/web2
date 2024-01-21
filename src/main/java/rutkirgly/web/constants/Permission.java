package rutkirgly.web.constants;

import lombok.Getter;

@Getter
public enum Permission {

    ADMIN_WRITE("admin:write"),
    ADMIN_WATCH("admin:read"),
    USER_WRITE("user:write"),
    USER_WATCH("user:read"),
    SELLER_WATCH("seller:read"),
    SELLER_WRITE("seller:read"),
    CHANGE_USER_ROLE("change_user_role");

    private final String permission;
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String CHANGE_USER_ROLE_PERMISSION = "CHANGE_USER_ROLE";
    public static final String ADMIN_WRITE_PERMISSION = "admin:write";


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
