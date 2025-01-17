package game;

public class Game {
    private String id;        // 게임 ID
    private String title;     // 게임 제목
    private String genre;     // 장르
    private double rating;    // 평점

    public Game(String id, String title, String genre, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    // Getter 메서드
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    // toString 메서드 (출력 형식 지정)
    @Override
    public String toString() {
        return String.format("ID: %s, Title: %s, Genre: %s, Rating: %.1f", id, title, genre, rating);
    }
}
