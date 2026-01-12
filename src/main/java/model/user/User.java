package model.user;

import model.movie.Movie;
import org.jetbrains.annotations.NotNull;
import repository.Repository;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class User {
    public String username;
    public String encodedPassword;
    public List<String> watchedFilms;
    public int accessLevel = 1;

    private int id;


    public User(String name, String password) {
        this.encodedPassword = encode(password);
        this.username = name;
        this.watchedFilms = new ArrayList<>();
    }
    public User(String name, String encodedPassword, List<String> watchedFilms, int accessLevel) {
        this.encodedPassword = encodedPassword;
        this.username = name;
        this.watchedFilms = watchedFilms;
        this.accessLevel = accessLevel;
    }


    public boolean checkPassword(String password) {
        return password.equals(decode(encodedPassword));
    }
    protected String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }
    protected String decode(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
    protected void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(decode(this.encodedPassword))) {
            this.encodedPassword = encode(newPassword);
        } else throw new SecurityException("Entered old Password does not equal Password");
    }
    protected void changeUsername(String newUsername, String password) {
        if (password.equals(decode(encodedPassword))) {
            this.username = newUsername;
        } else throw new SecurityException("Wrong password, access denied");
    }

    public void addWatchedFilms(@NotNull List<String> titles) {
        for (String title : titles) {
            System.out.println("Searching for movie " + title);
            try {
                Movie movie = Repository.getMovie(title);
                System.out.println(movie.title + "; " + movie.Id);
                if (movie == null) {
                    System.out.println("Movie not found: [" + title + "]");
                    return;
                }
                System.out.println(watchedFilms);
                this.watchedFilms.add(movie.title);
                System.out.print("found " + movie.title);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void deleteWatchedFilms(@NotNull List<String> seenTitles) {
        for (String seenTitle : seenTitles) {
            try {
                watchedFilms.remove(seenTitle);
            } catch (Exception e) {
                System.out.print("Title " + seenTitle + " has not been watched yet: " + e.getMessage());
            }
        }
    }
}


