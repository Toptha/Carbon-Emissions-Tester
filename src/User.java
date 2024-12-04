public class User {
    private String userId;
    private String username;
    private String role;
    public User(String userId, String username, String role) {
        this.userId=userId;
        this.username=username;
        this.role=role;
    }
    public void displayDetails() {
        System.out.println("User ID: "+userId);
        System.out.println("Name: "+username);
        System.out.println("Role: "+role);
    }
}
