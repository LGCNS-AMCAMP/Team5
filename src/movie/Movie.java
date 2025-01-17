package movie;

record Movie(String id, String title, String genre, double rating) {
    @Override
    public String toString() {
        return String.format("ID: %s | 제목: %s | 장르: %s | 평점: %.1f", id, title, genre, rating);
    }
}