import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection_instance = null;
    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getDatabaseConnection_instance() {
        if (databaseConnection_instance == null) {
            databaseConnection_instance = new DatabaseConnection();
        }
        return databaseConnection_instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/knowledge_base", "root", "root");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return connection;
    }
}
