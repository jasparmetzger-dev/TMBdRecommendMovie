package model.user;

import repository.Repository;

import java.util.List;

public class Admin extends User {

    public Admin(String name, String password) {
        super(name, password);
        accessLevel = 10;
    }
    public Admin(String name, String encodedPassword, List<String> watchedFilms, int accessLevel) {
        super(name, encodedPassword, watchedFilms, accessLevel);
    }

    private void changeUserPassword(User user, String newPassword) {
        if (user.accessLevel >=  accessLevel) throw new SecurityException("You do not have access to change this password");
        String password = user.decode(user.encodedPassword);
        user.changePassword(password, newPassword);
    }
    private void changeUserUsername(User user, String newUsername) {
        if (user.accessLevel >= accessLevel) throw new SecurityException("You do not have access to change this username");
        String password = user.decode(user.encodedPassword);
        user.changeUsername(newUsername, password);
    }
    private void addUser(String username, String password, userRoles role) {
        User toBeAdded = null;
        if (!Repository.isUserAdded(username)) {
            if (role == userRoles.ADMIN) {
                if (accessLevel > 2) {
                    User madeAdmin = new Admin(username, password);
                    madeAdmin.accessLevel -= 1;
                    Repository.addUser(madeAdmin);
                } else throw new SecurityException("Access Level too low");
            }
            else if (role == userRoles.USER) {
                User madeUser = new User(username, password);
                Repository.addUser(madeUser);
            }
        } else throw new IllegalArgumentException("This username already exists.");
    }
    private void deleteUser(User user) {
        if (user.accessLevel >= accessLevel) throw new SecurityException("You do not have access to delete this user");
        try {
            Repository.deleteUser(user);
        } catch (Exception e) {
            System.out.print("Cannot delete this user: " + e.getMessage());
        }
    }


}
