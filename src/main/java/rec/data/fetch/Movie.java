package rec.data.fetch;

public class Movie {
    public int tmbdId;
    public String title;
    public String genres;
    public double rating;

    Movie(int tmbdId, String title, String genres, double rating) {
        this.tmbdId = tmbdId;
        this.title = title;
        this.genres = genres;
        this.rating = rating;
    }

}
