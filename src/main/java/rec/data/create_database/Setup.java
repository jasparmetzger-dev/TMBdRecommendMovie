package rec.data.create_database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {
    public static void createMoviesTable (Connection conn) {

        String sql = """
                    CREATE TABLE IF NOT EXISTS movies (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tmbd_id INTEGER NOT NULL,
                    title TEXT NOT NULL,
                    rating REAL);
                    """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
        catch (SQLException e) {
            System.out.print("Table creation failed: " + e.getMessage());
        }

    }
}
