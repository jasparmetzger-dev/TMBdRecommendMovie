package repository;

import data.manage.DatabaseDAO;
import model.movie.Movie;
import model.user.Admin;
import model.user.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Repository {

    public static List<User> userList = new ArrayList<>();

    public Repository() {
        userList.add(new Admin("root", "1234"));
    }

    public static void addUser(User user) {
        userList.add(user);
    }
    public static void deleteUser(User user) {
        userList.remove(user);
    }
    public static User getUser(String username) {
        if (isUserAdded(username)) {
           for (User user : userList) {
               if (user.username.equals(username)) {
                   return user;
               }
           }
        } throw new NoSuchElementException("User not found");
    } //connect to db


    public static Movie getMovie(String title) throws SQLException {
        return DatabaseDAO.getMoviebyTitle(title);
    }
    public static boolean isUserAdded(String username) {
        for (User addedUser : userList) {
            if (addedUser.username.equals(username)) return true;
        } return false;
    }

}
