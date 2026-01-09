package data.download;

import model.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MovieWriter {

    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    public static void writeMovieBatch(List<Movie> movies) {

        String sql = """
                INSERT OR IGNORE INTO movies (tmdb_id, title, rating, genres, genre_ids, genre_vector)
                VALUES ( ? , ? , ? , ? , ? , ? )
                """;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (Movie m : movies) {

                String idString = intArrToString(m.genreIds);
                String vectorString = doubleArrToString(m.genreVector.getDoubles());

                stmt.setInt(1, m.tmdbId);
                stmt.setString(2, m.title);
                stmt.setDouble(3, m.rating);
                stmt.setString(4, m.genres);
                stmt.setString(5, idString);
                stmt.setString(6, vectorString);

                stmt.addBatch();
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String doubleArrToString(double[] arr) {
        if (arr == null || arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            sb.append(",").append(arr[i]);
        }
        return sb.toString();
    }
    public static String intArrToString(int[] arr) {
        if (arr == null || arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            sb.append(",").append(arr[i]);
        }
        return sb.toString();
    }
}
