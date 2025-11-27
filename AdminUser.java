package healthadmin;

// 5. A final permitted subclass
public final class AdminUser extends User {
    public AdminUser(String username) {
        super(username); // 1. super()
    }

    @Override
    public String getPermissions() {
        return "Read, Write, Execute";
    }
}