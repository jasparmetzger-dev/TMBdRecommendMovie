package data.manage;

import model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageUserDb {

    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    static void main(String[] args) throws SQLException {
    }

    public static void makeDb() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS user (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE,
                encoded_password TEXT,
                seen_movies TEXT,
                access_level INTEGER)
                """;
        DatabaseDAO.executeDbStatement(sql);
    }

    public static void insertUser(User user) throws SQLException {
        String sql = """
                INSERT OR REPLACE INTO user (username, encoded_password, seen_movies, access_level)
                VALUES ( ? , ? , ? , ? )
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            StringBuilder sb = new StringBuilder();
            for (String title : user.watchedFilms) {
                sb.append(title);
            }
            stmt.setString(1, user.username);
            stmt.setString(2, user.encodedPassword);
            stmt.setString(3, sb.toString());
            stmt.setInt(4, user.accessLevel);

        } catch (SQLException e) {
            System.out.print("Connection failed: " + e.getMessage());
            throw new SQLException(e);
        }
    }

    public static List<User> getAllUser() throws SQLException {
        String sql = "SELECT * FROM user";
        List<User> allUser = new ArrayList<>();

        try (Connection conn = DatabaseDAO.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = makeUser(rs);
                allUser.add(u);
            }
        }

        return allUser;
    }

    public static User makeUser(ResultSet rs) throws SQLException {
        User u = new User(
                rs.getString("username"),
                rs.getString("encoded_password"),
                TypeConversionHelpers.StringToListString(rs.getString("seen_movies")),
                rs.getInt("access_level")
        );
        return u;
    }
}
