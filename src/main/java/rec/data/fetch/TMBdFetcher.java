package rec.data.fetch;

import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class TMBdFetcher {
    private static final String TMBD_API_KEY = Dotenv.load().get("TMBD_API_KEY");

    public static JSONObject getMovie(int tmbdId) {
        try {
            //server connection with ChatGPT

            HttpClient client = HttpClient.newHttpClient();
            String url = "https://api.themoviedb.org/3/movie/" + tmbdId + "?api_key=" + TMBD_API_KEY;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return new JSONObject(response.body());

        } catch (Exception e) {
            System.out.print("Fetching failed: " + e);
            return null;
        }
    }

    public Movie parseMovie(@NotNull JSONObject movieJSON) {
        int tmbdId = movieJSON.getInt("id");
        String title = movieJSON.optString("title", "Unknown");
        double rating = normalizeRating(movieJSON.optDouble("vote_average", 0.0));

        String genres = "";
        if (movieJSON.has("genres")) {

            StringBuilder sb = new StringBuilder();
            JSONArray arr = movieJSON.getJSONArray("genres");

            for (int i = 0; i < arr.toList().size(); i++) {
                String genre = arr.getJSONObject(i).getString("name");
                sb.append(genre);
                if (i < arr.toList().size() - 1) sb.append(", ");
            }
            genres = sb.toString();
        }

        return new Movie(tmbdId, title, genres, rating);
    }

    public double normalizeRating(double rating) {
        final double maxRating = 10;
        final double minRating = 0;
        return rating / maxRating;

    }
}
