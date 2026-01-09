package data2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;
import io.github.cdimascio.dotenv.Dotenv;
import model.Movie;

import java.util.Arrays;
import java.util.List;

public class FetchData {
    public FetchData() throws MovieDbException, JsonProcessingException {}

    private static final String TMBD_API_KEY = Dotenv.load().get("TMBD_API_KEY");

    public static List<Movie> fetchMovieBatch(int page) {
         TheMovieDbApi api;
         try {
             api = new TheMovieDbApi(TMBD_API_KEY);
         } catch (MovieDbException e) {
             throw new RuntimeException(e);
         }

         ResultList<MovieInfo> MovieInfoLst;
         try {
             MovieInfoLst = api.getPopularMovieList(page, "DE");
         } catch (MovieDbException e) {
             throw new RuntimeException(e);
         }

         ObjectMapper mapper = new ObjectMapper();
         String json;
         try {
             json = mapper.writerWithDefaultPrettyPrinter()
                     .writeValueAsString(MovieInfoLst.getResults());
         } catch (JsonProcessingException e) {
             throw new RuntimeException(e);
         }

         List<Movie> movies = MovieInfoLst.getResults().stream().map(
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

         return movies;
    }


}
