package game;

import java.util.*;

public class GameManagement {
    private List<Game> savedGames = new ArrayList<>();
    private Set<String> gameIds = new HashSet<>();
    private final Scanner scanner;

    public GameManagement(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n게임 관리 시스템");
            System.out.println("1. 게임 추가");
            System.out.println("2. 저장된 게임 목록 보기");
            System.out.println("3. 게임 삭제");
            System.out.println("4. 게임 검색");
            System.out.println("X. 종료");
            System.out.print("선택: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    addGame();
                    break;
                case "2":
                    listSavedGames();
                    break;
                case "3":
                    deleteGame();
                    break;
                case "4":
                    searchGames();
                    break;
                case "X":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    /**
     * 게임 추가
     */
    public void addGame() {
        System.out.print("게임 ID: ");
        String id = scanner.nextLine();

        // 게임 ID 중복 체크
        if (gameIds.contains(id)) {
            System.out.println("이미 등록된 게임 ID입니다.");
            return;
        }

        System.out.print("게임 제목: ");
        String title = scanner.nextLine();

        System.out.print("장르: ");
        String genre = scanner.nextLine();

        System.out.print("평점 (0.0 ~ 5.0): ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // 버퍼 정리

        // Game 객체 생성 및 등록
        Game game = new Game(id, title, genre, rating);
        savedGames.add(game);
        gameIds.add(id);
        System.out.println("게임이 등록되었습니다: " + game);
    }

    /**
     * 저장된 게임 목록 보기
     */
    public void listSavedGames() {
        if (savedGames.isEmpty()) {
            System.out.println("등록된 게임이 없습니다.");
            return;
        }
        // 모든 게임 출력
        System.out.println("\n===== 저장된 게임 목록 =====");
        savedGames.forEach(System.out::println);
    }

    /**
     * 게임 검색
     * 제목 또는 장르에 사용자가 입력한 키워드가 포함된 게임을 검색
     */
    public void searchGames() {
        System.out.print("검색어를 입력하세요 (제목/장르): ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        // 게임 목록에서 키워드와 일치하는 제목 또는 장르 찾기
        for (Game game : savedGames) {
            if (game.getTitle().toLowerCase().contains(keyword) ||
                    game.getGenre().toLowerCase().contains(keyword)) {
                System.out.println(game);
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    /**
     * 게임 삭제
     */
    public void deleteGame() {
        System.out.print("삭제할 게임 ID를 입력하세요: ");
        String id = scanner.nextLine();

        // 게임 ID에 해당하는 게임 삭제
        Iterator<Game> iterator = savedGames.iterator();
        while (iterator.hasNext()) {
            Game game = iterator.next();
            if (game.getId().equals(id)) {
                iterator.remove();
                gameIds.remove(id);
                System.out.println("게임이 삭제되었습니다: " + game);
                return;
            }
        }
        System.out.println("해당 ID의 게임을 찾을 수 없습니다.");
    }

    /**
     * 프로그램 실행 진입점
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManagement management = new GameManagement(scanner);
        management.start();
    }
}