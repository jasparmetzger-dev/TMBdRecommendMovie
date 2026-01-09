package repository;

import model.Movie;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public static List<User> userList = new ArrayList<>();

    public Repository() {}

    public static void addUser(User user) {
        userList.add(user);
    }
    public static void deleteUser(User user) {
        userList.remove(user);
    }


    public static Movie findMovie(String title) {
        return new Movie();
    }
    public static boolean isUserAdded(String username) {
        for (User addedUser : userList) {
            if (addedUser.username.equals(username)) return true;
        } return false;
    }

}
