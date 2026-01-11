package data.manage;

import java.sql.*;

public class ManageUserDb {
    static void main(String[] args) throws SQLException {
        makeDb();
    }

    public static void makeDb() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS user (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE,
                encoded_password TEXT,
                seen_movies TEXT)
                """;
        DatabaseDAO.executeDbStatement(sql);
    }

    public static void


}
