package cli;


import authservice.AuthService;
import data.download.MakeMoviesDb;
import data.manage.ManageUserDb;
import model.movie.Movie;
import model.user.*;
import repository.Repository;

import java.util.List;

public class Main {

    static void main(String[] args) throws Exception {
        Repository rep = new Repository();

        User user = LoginInterface.makeInterface();

        if (AuthService.loggedIn) {

            UserInterface ui = (user.getClass() == Admin.class) ? new AdminInterface() : new UserInterface();
            ui.makeInterface();

        }
    }
}
