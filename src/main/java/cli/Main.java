package cli;


import authservice.AuthService;
import model.user.*;

public class Main {

    static void main(String[] args) throws Exception {

        User user = LoginInterface.makeInterface();

        if (AuthService.loggedIn) {

            UserInterface ui = (user.getClass() == Admin.class) ? new AdminInterface() : new UserInterface();
            ui.makeInterface();

        }
    }
}
