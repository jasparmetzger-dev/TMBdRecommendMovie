package data.create_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:movies.db";

    static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            System.out.print("Connected to DB.");
            return connection;
        }
        catch (SQLException e) {
            System.out.print("Connection failed.");
            return null;
        }
    }
}
