package model;

import java.util.Map;

public class Mappings {
    public static final Map<Integer, Integer> genreIdMap = Map.ofEntries(
                Map.entry(28, 0),    // Action
                Map.entry(12, 1),    // Adventure
                Map.entry(16, 2),    // Animation
                Map.entry(35, 3),    // Comedy
                Map.entry(80, 4),    // Crime
                Map.entry(99, 5),    // Documentary
                Map.entry(18, 6),    // Drama
                Map.entry(10751, 7), // Family
                Map.entry(14, 8),    // Fantasy
                Map.entry(36, 9),    // History
                Map.entry(27, 10),   // Horror
                Map.entry(10402, 11),// Music
                Map.entry(9648, 12), // Mystery
                Map.entry(10749, 13),// Romance
                Map.entry(878, 14),  // Science Fiction
                Map.entry(10770, 15),// TV Movie
                Map.entry(53, 16),   // Thriller
                Map.entry(10752, 17),// War
                Map.entry(37, 18)    // Western
    );
    public static final Map<Integer, String> genreNameMap = Map.ofEntries(
                Map.entry(28, "Action"),
                Map.entry(12, "Adventure"),
                Map.entry(16, "Animation"),
                Map.entry(35, "Comedy"),
                Map.entry(80, "Crime"),
                Map.entry(99, "Documentary"),
                Map.entry(18, "Drama"),
                Map.entry(10751, "Family"),
                Map.entry(14, "Fantasy"),
                Map.entry(36, "History"),
                Map.entry(27, "Horror"),
                Map.entry(10402, "Music"),
                Map.entry(9648, "Mystery"),
                Map.entry(10749, "Romance"),
                Map.entry(878, "Science Fiction"),
                Map.entry(10770, "TV Movie"),
                Map.entry(53, "Thriller"),
                Map.entry(10752, "War"),
                Map.entry(37, "Western")
                );
}

