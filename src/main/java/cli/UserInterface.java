package cli;

import algo.RecommendationAlgorithm;
import authservice.AuthService;
import model.movie.Movie;
import repository.Repository;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface implements Interface {
    public record Action(String action) {}

    public  void makeInterface() throws Exception {

        String action = showHomepage().action;

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

    public  Action showHomepage() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n====  homepage ====\n");
        System.out.println(">> to return press 'r'. \n");
        System.out.println(">> possible actions:");
        System.out.println(">> edit your list of seen movies (1)");
        System.out.println(">> get movies recommended for you (2)");
        System.out.println(">> logout (3)");
        System.out.print(">> what would you like to do (1/2/3): ");

        String s = sc.next();
        System.out.print("\n");
        return new Action(s);
    }

    public  void getRecommended() throws Exception {

        RecommendationAlgorithm algo = new RecommendationAlgorithm(AuthService.loggedInUser);

        System.out.println("\n>> searching for " + algo.GET_MOVIE_AMOUNT + " of the best movies specialized for you...");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(">> computing...\n\n");
        List<Movie> bestMovies = algo.findBestMovies();
        System.out.println(">> found best movies: \n");

        for (int i = 0; i < algo.GET_MOVIE_AMOUNT; i++) {
            Movie m = bestMovies.get(i);
            System.out.println(">> place number " + (i+1) + ": "+ m.title);
        }

        System.out.println(">> hope you like them :)\n");
        TimeUnit.MILLISECONDS.sleep(1000);

        makeInterface();
    }

    public  void editList() throws Exception {

        Scanner sc = new Scanner(System.in);
        List<String> seenLst = AuthService.loggedInUser.watchedFilms;
        System.out.println("\n>> printing your seen movies:\n");

        for (int i = 0; i < seenLst.size(); i++) {
            System.out.println(">> " + (i+1) + ". movie: " + seenLst.get(i));
        }

        System.out.println("\n\n>> what would you like to do?");
        System.out.print(">> possible actions: add (1), delete(2), return(r)... ");
        String action = sc.next();
        System.out.print("\n");

        switch (action) {
            case "r" -> makeInterface();
            case "1" -> {
                System.out.println("\n enter your additional watched movies here (film1, film2, film3):\n");
                String titles = sc.next();
                String[] titleArr = titles.toLowerCase().split(", ");
                AuthService.loggedInUser.addWatchedFilms(titleArr);
            }
            case "2" -> {
                System.out.println("\n enter movies to be deleted here (film1, film2, film3):\n");
                String titles = sc.next();
                String[] titleArr = titles.toLowerCase().split(", ");
                AuthService.loggedInUser.deleteWatchedFilms(titleArr);
            }
            case null, default -> {
                System.out.println(">> invalid input.");
                makeInterface();
            }
        }

    }

}
