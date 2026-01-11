package cli;

import authservice.AuthService;
import repository.Repository;

import java.util.Scanner;

public class AdminInterface extends UserInterface implements Interface {

    @Override
    public void makeInterface() throws Exception {
        String action = showHomepage().action();

        switch (action) {
            case "1" -> editList();
            case "2" -> getRecommended();
            case null, default -> {
                Repository.saveUserList();
                AuthService.logout();
                LoginInterface.makeInterface();
            }
        }
    }

    @Override
    public Action showHomepage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n====  homepage for admin ====\n");
        System.out.println(">> to return press 'r'. \n");
        System.out.println(">> possible actions:");
        System.out.println(">> edit your list of seen movies (1)");
        System.out.println(">> get movies recommended for you (2)");
        System.out.println(">> logout (3)");
        System.out.println(">> 'insert admin actions'");
        System.out.print(">> what would you like to do (1/2/3): ");

        String s = sc.next();
        System.out.print("\n");
        return new Action(s);
    }

}
