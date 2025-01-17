package music;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MusicMain {

    private MusicManagement music = new MusicManagement();

    public void showMenu(Scanner scanner) {

        while (true) {
            try {
                printMenu();

                int menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
                        music.addMusic(scanner);
                        break;
                    case 2:
                        music.getMusicList();
                        break;
                    case 3:
                        music.searchMusic(scanner);
                        break;
                    case 4:
                        music.updateMusic(scanner);
                        break;
                    case 5:
                        music.deleteMusic(scanner);
                        break;
                    case 6:
                        System.out.println("메인으로 돌아갑니다.\n");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("유효하지 않은 입력입니다. 숫자를 입력해주세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--------------");
        System.out.println("최진실 - 음악관리");
        System.out.println("--------------");
        System.out.println("1. 음악 등록");
        System.out.println("2. 전체 음악 조회");
        System.out.println("3. 음악 검색");
        System.out.println("4. 음악 수정");
        System.out.println("5. 음악 삭제");
        System.out.println("6. 메인으로");
        System.out.print("메뉴를 선택하세요: ");
    }
}