package music;

import java.util.*;

public class MusicManagement {

    private List<Music> musics = new ArrayList<>();

    private Set<String> idSet = new HashSet<>();

    String createId() {
        if (musics.isEmpty()) {
            return "1";
        }

        String lastId = musics.get(musics.size() - 1).getId();
        int newId = Integer.parseInt(lastId) + 1;

        return String.valueOf(newId);
    }

    public void addMusic(Scanner scanner) {

        String id = createId();

        System.out.print("곡 제목: ");
        String title = scanner.nextLine();

        System.out.print("가수 명: ");
        String artist = scanner.nextLine();

        System.out.print("곡 장르: ");
        String genre = scanner.nextLine();

        Music music = new Music(id, title, artist, genre);
        musics.add(music);

        idSet.add(music.getId());
        System.out.println("곡이 정상적으로 등록되었습니다." + music);

    }

    // 전체 음악 조회 로직
    public void getMusicList() {
        if (musics.isEmpty()) {
            System.out.println("등록된 곡이 없습니다.");
            return;
        }
        musics.forEach(System.out::println);
    }

    // 음악 검색
    public void searchMusic(Scanner scanner) {
        System.out.print("검색어를 입력하세요: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        boolean isMusicFound = false;

        for (Music music : musics) {
            if (music.getTitle().trim().toLowerCase().contains(keyword) ||
                    music.getArtist().trim().toLowerCase().contains(keyword) ||
                    music.getGenre().trim().toLowerCase().contains(keyword)) {

                System.out.println(music);
                isMusicFound = true;
            }
        }

        if (!isMusicFound) {
            System.out.println("검색 결과가 없습니다.");
        }
    }


    // 음악 수정 로직
    public void updateMusic(Scanner scanner) {
        System.out.printf("수정할 곡의 ID를 입력하세요: ");
        String musicId = scanner.nextLine();

        for (int i = 0; i < musics.size(); i++) {
            if (musics.get(i).getId().equals(musicId)) {
                System.out.println("\n1. 곡 제목");
                System.out.println("2. 가수 명");
                System.out.println("3. 곡 장르");
                System.out.printf("수정할 항목을 선택해주세요: ");

                int menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
                        System.out.printf("%s -> ", musics.get(i).getTitle());
                        String title = scanner.nextLine();
                        musics.get(i).setTitle(title);
                        break;
                    case 2:
                        System.out.printf("%s -> ", musics.get(i).getArtist());
                        String artist = scanner.nextLine();
                        musics.get(i).setArtist(artist);
                        break;
                    case 3:
                        System.out.printf("%s -> ", musics.get(i).getGenre());
                        String genre = scanner.nextLine();
                        musics.get(i).setGenre(genre);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        return;
                }

                System.out.println("곡이 정상적으로 수정되었습니다." + musics.get(i));
                return;
            }
        }


        System.out.printf("ID가 %s인 곡을 찾을 수 없습니다.\n", musicId);
    }

    // 음악 삭제 로직
    public void deleteMusic(Scanner scanner) {
        System.out.print("삭제할 곡의 ID를 입력하세요: ");
        String musicId = scanner.nextLine();

        Iterator<Music> iterator = musics.iterator();
        while (iterator.hasNext()) {
            Music music = iterator.next();

            if (music.getId().equals(musicId)) {
                System.out.printf("삭제 곡 정보: %s\n", music);
                while (true) {
                    System.out.print("삭제할 곡의 정보가 맞습니까? (y/n): ");
                    String delYn = scanner.nextLine().trim().toLowerCase();

                    if (delYn.equals("y")) {
                        iterator.remove();
                        System.out.println("곡이 삭제되었습니다.");
                        return;
                    } else if (delYn.equals("n")) {
                        System.out.println("삭제가 취소되었습니다.");
                        return;
                    } else {
                        System.out.println("잘못된 입력입니다. 'y' 또는 'n'을 입력해주세요.");
                    }
                }
            }
        }

        System.out.printf("ID가 %s인 곡을 찾을 수 없습니다.\n", musicId);
    }
}