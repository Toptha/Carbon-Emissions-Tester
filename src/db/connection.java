package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connection {
    private static final String URL = "jdbc:mysql://localhost:3306/carbon_offset_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
