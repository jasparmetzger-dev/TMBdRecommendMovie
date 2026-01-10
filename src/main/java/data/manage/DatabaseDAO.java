package data.manage;

import io.github.cdimascio.dotenv.Dotenv;
import model.movie.Movie;
import model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.manage.TypeConversionHelpers.StringToIntArr;

public class DatabaseDAO {

    private static final String TMBD_API_KEY = Dotenv.load().get("TMBD_API_KEY");
    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    static void main(String[] args) {

    }

    public static Movie getMoviebyId(int id) throws SQLException {
        String sql = """
                SELECT * 
                FROM MOVIES
                WHERE id = ?
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return makeResultSetMovie(rs);
                } else return null;
            }
        }
        catch (SQLException e) {
            System.out.print("Inserting failed: " );
            e.printStackTrace();
        }
        return null;
    }
    public static Movie getMoviebyTitle(String title) throws SQLException {
        String sql = """
                SELECT * 
                FROM MOVIES
                WHERE title = ?
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return makeResultSetMovie(rs);
                } else return null;
            }
        }
        catch (SQLException e) {
            System.out.print("Inserting failed: " );
            e.printStackTrace();
        }
        return null;
    }
    public static List<Movie> getAllMovies() throws SQLException {
        String sql = """
                SELECT * FROM MOVIES
                """;
        ResultSet rs = executeDbQuery(sql);
        List<Movie> allMovies = new ArrayList<>();
        while (rs.next()) {
            Movie m = makeResultSetMovie(rs);
            allMovies.add(m);
        }
        return allMovies;
    }

    public static User getUserbyId(int id) {
        return null;
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
    public static void executeDbStatement(String sql) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.print("Inserting failed: " );
            e.printStackTrace();
        }
    }
    public static ResultSet executeDbQuery(String sql) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.print("Inserting failed: " );
            e.printStackTrace();
        }
        return null;
    }

    static Movie makeResultSetMovie(ResultSet rs) throws SQLException {
        Movie m = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getDouble("rating"),
                StringToIntArr(rs.getString("genre_ids"))
        );
        return m;
    }
}
