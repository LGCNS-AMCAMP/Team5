public class Game {
    private String id;
    private String name;
    private String released;
    private String rating;

    public Game(String id, String name, String released, String rating) {
        this.id = id;
        this.name = name;
        this.released = released;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleased() {
        return released;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Released: %s, Rating: %s", id, name, released, rating);
    }
}
