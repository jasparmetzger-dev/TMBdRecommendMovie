package cli;

import authservice.AuthService;
import model.user.*;
import repository.Repository;

import java.rmi.AccessException;
import java.util.Scanner;

public class LoginInterface implements Interface {

    public record MakeUserData(String name, String pw, userRoles rl) {}

    public static User makeInterface() throws AccessException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n====  login interface  ====\n");
        System.out.print(">> Login (l) or make make account(a): ");
        char response = sc.next().charAt(0);
        System.out.print("\n");

        User user = null;
        if (response == 'l') {
            user = loginUser();
        } else if (response == 'a') {
            user = makeNewAccount();
        }
        return user;
    }

    public static User loginUser() throws AccessException {
        System.out.println("\n====  login as user ====\n");
        MakeUserData data = getMakeUserData();

        return AuthService.login(data.name, data.pw, data.rl);
    }
    public static User makeNewAccount() throws AccessException {

        System.out.println("\n====  create new user ====\n");

        MakeUserData data = getMakeUserData();

        boolean userExists = false;
        for (User user : Repository.userList) {
            if (Repository.isUserAdded(data.name)) {
                userExists = true;
                break;
            }
        }
        if (userExists) throw new AccessException("user already exists.");

        makeUser(data);
        return loginUser();
    }

    public static void makeUser(MakeUserData data) {
        User makeUser = null;
        if (data.rl == userRoles.USER) {
            makeUser = new User(data.name, data.pw);
        } else if (data.rl == userRoles.ADMIN) {
            makeUser = new User(data.name, data.pw);
        }
        Repository.addUser(makeUser);
    }
    public static MakeUserData getMakeUserData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("username: ");
        String username = sc.next();
        System.out.print("\n password: ");
        String password = sc.next();
        System.out.print("\n role (admin/user): ");
        String role = sc.next();

        userRoles enumRole;
        if (role.equals("admin")) {
            enumRole = userRoles.ADMIN;
        } else if (role.equals("user")) {
            enumRole = userRoles.USER;
        } else {
            throw new IllegalArgumentException("role must either be user or admin");
        }
        return new MakeUserData(username, password, enumRole);
    }
}
