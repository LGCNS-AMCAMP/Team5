package game;

import lombok.Getter;

@Getter
public class VideoGame {
    private String id;
    private String title;
    private String genre;
    private double rating;

    public VideoGame (String id, String title, String genre, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %s, 장르: %s, 평점: %.1f)", title, id, genre, rating);
    }
}