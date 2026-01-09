package algo;

import model.*;
import repository.Repository;

import java.util.*;

public class RecommendationAlgorithm {
    User user;
    List<Movie> seenMovies;


    public RecommendationAlgorithm(User user) {
        this.user = user;

        this.seenMovies = new ArrayList<>();
        for (String movieTitle : user.watchedFilms) {
            seenMovies.add(Repository.getMovie(movieTitle));
        }
    }


}
