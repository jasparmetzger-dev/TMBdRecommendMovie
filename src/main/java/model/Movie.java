package model;

import math.Vector;

import java.util.List;

public class Movie {
    public int tmbdId;
    public String title;
    public String genres;
    public double rating;
    public Vector genreVector;

    public Movie(int tmbdId, String title, String genres, double rating) {
        this.tmbdId = tmbdId;
        this.title = title;
        this.genres = genres;
        this.rating = rating;
    }

    private Vector makeGenreVector(String genres) {
        String[] genreList = genres.trim().split(",");
        Vector v = new Vector(new double[19]);
        for (String genre : genreList) {
            int idx = GenreVector.GenreIdx.get(genre);
            v.setElement(idx, 1);
        }
        v.normalize();
        return v;
    }


}
