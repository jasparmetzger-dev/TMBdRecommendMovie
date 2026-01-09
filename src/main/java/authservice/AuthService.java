package authservice;

import model.*;
import repository.Repository;

import java.rmi.AccessException;
import java.util.Scanner;

public class AuthService {
    public static boolean loggedIn;
    private static User loggedInUser;

    public void login(String username, String password, userRoles role) throws AccessException {
        if (loggedIn) throw new AccessException("Already logged in");

        User user = Repository.getUser(username);
        if (!user.checkPassword(password)) {
            throw new AccessException("Wrong Password");
        }
        loggedIn = true;
        loggedInUser = user;
    }
    public void logout() throws Exception{
        if (!loggedIn) {
            throw new Exception("Not logged in");
        }
        loggedIn = false;
        loggedInUser = null;

    }

}
