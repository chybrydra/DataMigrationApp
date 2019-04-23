package pl.lukaszgrymulski.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static Connection connection = null;

    private DatabaseConnection() { }

    public static Connection getConnection() {

        if (connection == null) {
            String ipOfDatabase = "192.168.99.102";
            String portOfDatabase = "9001";
            String databaseName = "britenetrekr";
            String url = String.format(
                    "jdbc:mysql://%s:%s/%s?characterEncoding=UTF-8",
                    ipOfDatabase,
                    portOfDatabase,
                    databaseName
            );
            String user = "root";
            String password = "root";
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }

        return connection;
    }
}
