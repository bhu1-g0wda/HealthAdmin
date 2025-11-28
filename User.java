package healthadmin;

// 5. A sealed abstract class
public sealed abstract class User permits AdminUser, GuestUser {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
    public abstract String getPermissions();
}