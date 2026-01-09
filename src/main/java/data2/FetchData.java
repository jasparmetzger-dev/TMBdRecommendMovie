package data2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.methods.TmdbMovies;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;
import io.github.cdimascio.dotenv.Dotenv;
import model.Movie;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class FetchData {
    public FetchData() throws MovieDbException, JsonProcessingException {}

    private static final String TMBD_API_KEY = Dotenv.load().get("TMBD_API_KEY");

    static TheMovieDbApi api;
    static {
        try {
            api = new TheMovieDbApi(TMBD_API_KEY);
        } catch (MovieDbException e) {
            throw new RuntimeException(e);
        }
    }
    static ResultList<MovieInfo> MovieInfoLst;
    static {
        try {
            MovieInfoLst = api.getPopularMovieList(1, "DE");
        } catch (MovieDbException e) {
            throw new RuntimeException(e);
        }
    }

    static ObjectMapper mapper = new ObjectMapper();
    static String json;
    static {
        try {
            json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(MovieInfoLst.getResults());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Movie> movies = MovieInfoLst.getResults().stream().map(
            m -> {

                List<Integer> lst = m.getGenreIds();
                int[] arr = new int[lst.size()];
                for (int i = 0; i < lst.size(); i++) arr[i] = lst.get(i);

                Movie movie = new Movie(
                        m.getId(),
                        m.getTitle(),
                         arr,
                        m.getVoteAverage()
                );
                return movie;
            }
    ).toList();


    static void main(String[] args) throws MovieDbException {
        for (Movie m : movies) {
            System.out.print(m.tmdbId + ": ");
            System.out.print(Arrays.toString(m.genreIds) + "; ");
            System.out.print(m.genres + "\n");

        }

    }


    //private Movie normalizeMovie(TmdbMovies movies) {}
    //voteAverage, voteCount, genreIds mediaType=MOVIE
}
