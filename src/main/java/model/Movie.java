package model;

import math.Vector;

import java.util.List;

public class Movie {
    public int tmdbId;
    public String title;
    public double rating;
    public String genres;
    public int[] genreIds;
    public Vector genreVector;

    public Movie(int tmdbId, String title, int[] tmdbGenreIds, double rating) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.genreIds = getIdsFromTmdbId(tmdbGenreIds);
        this.genres = getGenresFromTmdbId(tmdbGenreIds);
        this.rating = rating;
        this.genreVector = makeGenreVector();
    }

    private int[] getIdsFromTmdbId(int[] Ids) {
        int[] privateIds = new int[Ids.length];
        for (int i = 0; i < Ids.length; i++) {

            privateIds[i] = Mappings.genreIdMap.get(Ids[i]);
        }
        return privateIds;
    }

    private String getGenresFromTmdbId(int[] Ids) {
        String[] genres = new String[Ids.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Ids.length; i++) {
            sb.append(Mappings.genreNameMap.get(Ids[i]));
            if (i != Ids.length -1) {
                sb.append(", ");
            }
        }
        return sb.toString();
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
