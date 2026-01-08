package rec.data.fetch;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

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
}
