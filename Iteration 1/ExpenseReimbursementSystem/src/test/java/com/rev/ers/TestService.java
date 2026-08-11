package com.rev.ers;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestService {
    private Connection conn;

    @BeforeAll
    public void SetUp() throws SQLException {
        Dotenv dotenv = Dotenv.configure().load();
        // Initialize H2 in-memory database for testing
        conn = DriverManager.getConnection(dotenv.get("DATABASE_URL"));

        // Create Tables
        // Users table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(50) NOT NULL, " +
                    "password VARCHAR(50) NOT NULL, " +
                    "firstname VARCHAR(30) NOT NULL, "+
                    "lastname VARCHAR(30) NOT NULL, " +
                    "role VARCHAR(10) NOT NULL, " +
                    "department INT REFERENCES departments(deptId));"
            );
        }

        // Departments Table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS departments (" +
                    "deptId INT PRIMARY KEY AUTO_INCREMENT, " +
                    "deptName VARCHAR(30) NOT NULL);"
            );
        }
        // Reimb Table
    }
}
