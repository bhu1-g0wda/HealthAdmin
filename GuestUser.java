package healthadmin;

// 5. A final permitted subclass
public final class GuestUser extends User {
    public GuestUser(String username) {
        super(username); // 1. super()
    }

    @Override
    public String getPermissions() {
        return "Read-Only";
    }
}