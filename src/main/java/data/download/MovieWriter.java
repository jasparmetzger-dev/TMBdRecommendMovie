package data.download;

import data.manage.TypeConversionHelpers;
import model.movie.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MovieWriter {

    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    public static void writeMovieBatch(List<Movie> movies) {

        String sql = """
                INSERT OR IGNORE INTO movies (title, rating, genre_ids)
                VALUES ( ? , ? , ? , ? , ? , ? )
                """;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (Movie m : movies) {

                String idString = TypeConversionHelpers.intArrToString(m.genreIds);

                stmt.setString(1, m.title);
                stmt.setDouble(2, m.rating);
                stmt.setString(3, idString);

                stmt.addBatch();
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
