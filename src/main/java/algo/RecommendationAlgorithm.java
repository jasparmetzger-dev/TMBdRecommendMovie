package algo;

import data.manage.DatabaseDAO;
import math.Vector;
import model.movie.Movie;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.Repository;

import java.sql.SQLException;
import java.util.*;

public class RecommendationAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(RecommendationAlgorithm.class);
    User user;
    Vector userVector;
    List<Movie> seenMovies;


    public RecommendationAlgorithm(User user) throws SQLException {
        this.user = user;

        List<Movie> seenMovies = new ArrayList<>();
        for (String movieTitle : user.watchedFilms) {
            seenMovies.add(Repository.getMovie(movieTitle));
        }
        this.seenMovies = seenMovies;
        this.userVector = makeUserVector();
    }

    public Vector makeUserVector() {
        Vector v = new Vector();
        for (Movie m : seenMovies) {
            v = Vector.addVectors(v, Vector.scalarMultiply(m.rating, m.genreVector));
        }
        v.normalize();
        return v;
    }

    public List<Movie> findBestMovies() throws SQLException {
        List<Movie> allMovies = DatabaseDAO.getAllMovies();

        record ScoreMovie(double score, Movie movie) {}
        List<ScoreMovie> scoreList = new ArrayList<>();

        for (Movie m : allMovies) {
            //core implementation
            Vector v = Vector.project(m.genreVector, userVector);
            double score = v.norm();
            scoreList.add(new ScoreMovie(score, m));
            scoreList.sort(Comparator.comparingDouble(ScoreMovie::score));
        }
        List<Movie> bestMovies = new ArrayList<>();
        int GET_MOVIE_AMOUNT = 3;
        //atleast three unwatched movies :). will make 3 variable maybe

        for (int i = 0; bestMovies.size() < GET_MOVIE_AMOUNT; i++) {
            Movie m = allMovies.get(i);
            if (!seenMovies.contains(m)) {
                bestMovies.add(m);
            }
        }
        return bestMovies;
    }



}
