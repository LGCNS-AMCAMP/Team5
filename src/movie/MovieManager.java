package movie;

import java.util.*;

public class MovieManager {
    private final List<Movie> movies = new ArrayList<>();
    private final Scanner scanner;

    public MovieManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("1. 영화 추가");
            System.out.println("2. 영화 목록 보기");
            System.out.println("3. 영화 삭제");
            System.out.println("4. 영화 검색");
            System.out.println("5. 평점순으로 영화 목록 보기");
            System.out.println("X. 메인으로");
            System.out.print("선택: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    listMovies(false);
                    break;
                case "3":
                    deleteMovie();
                    break;
                case "4":
                    searchMovie();
                    break;
                case "5":
                    listMovies(true);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private void addMovie() {
        System.out.print("영화 제목: ");
        String title = scanner.nextLine();

        System.out.print("영화 장르: ");
        String genre = scanner.nextLine();

        double rating;
        while (true) {
            System.out.print("영화 평점 (0.0 ~ 10.0): ");
            try {
                rating = Double.parseDouble(scanner.nextLine());
                if (rating < 0.0 || rating > 10.0) {
                    System.out.println("평점은 0.0에서 10.0 사이여야 합니다.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력해주세요.");
            }
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        movies.add(new Movie(id, title, genre, rating));
        System.out.println("영화가 추가되었습니다. (ID: " + id + ")");
    }

    private void listMovies(boolean sortByRating) {
        if (movies.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            return;
        }

        List<Movie> sortedMovies = new ArrayList<>(movies);
        if (sortByRating) {
            sortedMovies.sort(Comparator.comparingDouble(Movie::rating).reversed());
            System.out.println("\n=== 평점순 영화 목록 ===");
        } else {
            System.out.println("\n=== 영화 목록 ===");
        }

        sortedMovies.forEach(System.out::println);
    }

    private void deleteMovie() {
        if (movies.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            return;
        }

        listMovies(false);
        System.out.print("삭제할 영화의 ID를 입력하세요: ");
        String id = scanner.nextLine();

        boolean removed = movies.removeIf(movie -> movie.id().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("영화가 삭제되었습니다.");
        } else {
            System.out.println("해당 ID의 영화를 찾을 수 없습니다.");
        }
    }

    private void searchMovie() {
        System.out.print("검색할 영화 제목: ");
        String query = scanner.nextLine();

        List<Movie> results = movies.stream()
                .filter(movie -> movie.title().toLowerCase().contains(query.toLowerCase()))
                .toList();

        if (results.isEmpty()) {
            System.out.println("해당 제목의 영화를 찾을 수 없습니다.");
        } else {
            System.out.println("\n=== 검색 결과 ===");
            results.forEach(System.out::println);
        }
    }
}