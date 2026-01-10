package data.download;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeDb {

    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    static void main(String[] args) {
        try {
            Connection conn = connect();
            createMoviesTable(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static Connection connect() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            System.out.print("Connection to db successful. ");
            return conn;
        } catch (SQLException e) {
            System.out.print("Connection failed: " + e.getMessage());
            throw new SQLException(e);
        }
    }

    static void createMoviesTable(Connection conn) {

        String sql = """
                CREATE TABLE IF NOT EXISTS movies (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT UNIQUE NOT NULL,
                rating REAL,
                genre_ids TEXT
                )
                """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.print("Failed to execute statement: " + e.getMessage());
        }
    }
}
