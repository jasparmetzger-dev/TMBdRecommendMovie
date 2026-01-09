package data.download;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omertron.themoviedbapi.MovieDbException;
import model.Movie;

import java.util.List;

public class Main {
    static void main(String[] args) throws MovieDbException, JsonProcessingException {

        final int MAX_MOVIES = 2000;
        final int MAX_PAGE = (int) MAX_MOVIES / 20;


        for (int page = 1; page < MAX_PAGE; page++) {

            List<Movie> movies = FetchData.fetchMovieBatch(page);
            MovieWriter.writeMovieBatch(movies);

            System.out.print(">> Success: done with " + page * 20 + " Objects\n");
        }
    }
}
