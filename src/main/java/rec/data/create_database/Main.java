package rec.data.create_database;

import java.sql.Connection;

public class Main {
    static void main(String[] args) {
        Connection conn = Database.connect();
        if (conn != null) {
            Setup.createMoviesTable(conn);
        }
    }
}
