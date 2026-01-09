package model;

import org.jetbrains.annotations.NotNull;
import repository.Repository;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

public class User {
    public String username;
    protected String encodedPassword;
    public List<String> watchedFilms = new ArrayList<>();
    public int accessLevel = 1;

    private int id;
    static int autoincrement = 1;

    User(String name, String password) {
        this.encodedPassword = encode(password);
        this.username = name;

        this.id = autoincrement;
        autoincrement++;
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

    public void addWatchedFilms(@NotNull String[] titles) {
        for (String title : titles) {
            try {
                Movie movie = Repository.getMovie(title);
                this.watchedFilms.add(title);
            } catch (Exception e) {
                System.out.print("Couldn't find Movie " + title + ": " + e.getMessage());
            }
        }
    }
    public void deleteWatchedFilms(@NotNull String[] seenTitles) {
        for (String seenTitle : seenTitles) {
            try {
                watchedFilms.remove(seenTitle);
            } catch (Exception e) {
                System.out.print("Title " + seenTitle + " has not been watched yet: " + e.getMessage());
            }
        }
    }
}


