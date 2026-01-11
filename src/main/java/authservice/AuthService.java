package authservice;

import model.user.User;
import model.user.userRoles;
import repository.Repository;

import java.rmi.AccessException;

public class AuthService {
    public static boolean loggedIn;
    public static User loggedInUser;

    public static User login(String username, String password, userRoles role) throws AccessException {
        if (loggedIn) throw new AccessException("Already logged in");

        User user = Repository.getUser(username);
        if (!user.checkPassword(password)) {
            throw new AccessException("Wrong Password");
        }
        loggedIn = true;
        loggedInUser = user;
        return user;
    }
    public static void logout() throws Exception{
        if (!loggedIn) {
            throw new Exception("Not logged in");
        }
        loggedIn = false;
        loggedInUser = null;

    }

}
