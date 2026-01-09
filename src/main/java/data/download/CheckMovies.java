package data.download;

import java.sql.*;

public class CheckMovies {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:mydatabase.db";
        String sql = "SELECT tmdb_id, title, rating, genres, genre_ids, genre_vector " +
                "FROM movies LIMIT 10";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("tmdb_id") + ": " +
                                rs.getString("title") + " -> " +
                                rs.getDouble("rating") + "; " +
                                rs.getString("genres") + "; " +
                                rs.getString("genre_vector")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}