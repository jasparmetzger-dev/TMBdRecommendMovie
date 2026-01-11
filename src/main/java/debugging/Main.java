package debugging;

import data.manage.DatabaseDAO;
import data.manage.ManageUserDb;

import java.sql.SQLException;

public class Main {
    static void main(String[] args) throws SQLException {
        System.out.print(ManageUserDb.getAllUser());
        System.out.print(DatabaseDAO.getAllMovies());
    }
}
