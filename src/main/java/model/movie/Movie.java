package model.movie;

import math.Vector;

public class Movie {
    public int Id;
    public String title;
    public double rating;
    public String genres;
    public int[] genreIds;
    public Vector genreVector;

    public Movie(int Id, String title, double rating, int[] genreIds) {
        this.Id = Id;
        this.title = title;
        this.rating = rating;
        this.genreIds = genreIds;
        this.genres = Mappings.getGenresFromId(genreIds);
        this.genreVector = makeGenreVector();
    }
    public Movie(String title, double rating, int[] genreIds) {
        this.title = title;
        this.rating = rating;
        this.genreIds = genreIds;

    }


    private Vector makeGenreVector() {
        Vector v = new Vector(new double[19]);
        for (Integer genreIdx : genreIds) {
            v.setElement(genreIdx, 1);
        }
        v.normalize();
        return v;
    }
}
