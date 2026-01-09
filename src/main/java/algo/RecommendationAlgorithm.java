package algo;

import math.Vector;
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

    public Vector makeUserVector() {
        Vector v = new Vector(new double[19]); //19 genres
        for (Movie movie : seenMovies) {
            v = Vector.addVectors(v, Vector.scalarMultiply(movie.rating, movie.genreVector));
        }
        v.normalize();
        return v;
    }



}
