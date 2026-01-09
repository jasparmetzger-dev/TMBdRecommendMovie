package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class GenreVector {
    static String jsonString = """
            {
                "genres" : [
                    {"id": 28, "name": "Action"},
                    {"id": 12, "name": "Adventure"},
                    {"id": 16, "name": "Animation"},
                    {"id": 35, "name": "Comedy"},
                    {"id": 80, "name": "Crime"},
                    {"id": 99, "name": "Documentary"},
                    {"id": 18, "name": "Drama"},
                    {"id": 10751, "name": "Family"},
                    {"id": 14, "name": "Fantasy"},
                    {"id": 36, "name": "History"},
                    {"id": 27, "name": "Horror"},
                    {"id": 10402, "name": "Music"},
                    {"id": 9648, "name": "Mystery"},
                    {"id": 10749, "name": "Romance"},
                    {"id": 878, "name": "Science Fiction"},
                    {"id": 10770, "name": "TV Movie"},
                    {"id": 53, "name": "Thriller"},
                    {"id": 10752, "name": "War"},
                    {"id": 37, "name": "Western"}
                ]
            }
            """;



    public static JSONObject genres = new JSONObject(jsonString);
    public static JSONArray genresArray = genres.getJSONArray("genres");
    public static Map<String, Integer> GenreIdx = GenreIdxMap(genresArray);
    public static Map<Integer, String> IdxGenre = IdxGenreMap(genresArray);

    public static Map<String, Integer> GenreIdxMap(JSONArray arr) {
        Map<String, Integer> GenreIdx = new HashMap<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String genre = obj.getString("name");

            GenreIdx.put(genre, i);
        }
        return GenreIdx;
    }
    public static Map<Integer, String> IdxGenreMap (JSONArray arr) {
        Map<Integer, String> IdxGenre = new HashMap<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String genre = obj.getString("name");

            IdxGenre.put(i, genre);
        }
        return IdxGenre;
    }

}
