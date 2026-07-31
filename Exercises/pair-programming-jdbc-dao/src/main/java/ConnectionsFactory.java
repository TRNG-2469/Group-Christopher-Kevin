// ConnectionsFactory.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionsFactory {

    // Singleton instance placeholder
    private static ConnectionsFactory instance;

    // Read connection parameters from environment variables
    private final String url;
    private final String username;
    private final String password;

    // Private constructor prevents instantiation from outside
    private ConnectionsFactory() {
        this.url = "jdbc:postgresql://localhost/myDB";
        this.username = System.getenv("DB_USER");
        this.password = System.getenv("DB_PASS");

        // Fail-fast validation: Verify environment setup
        if (this.url == null || this.username == null || this.password == null) {
            throw new IllegalStateException(
                    "Critical Error: Database environment variables (DB_URL, DB_USER, DB_PASS) are not configured."
            );
        }

        // Force-load the PostgreSQL driver class (recommended in older frameworks)
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC driver not found on classpath.", e);
        }
    }

    // Global access point for the Singleton instance
    public static synchronized ConnectionsFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionsFactory();
        }
        return instance;
    }

    // Factory method returning a connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

