import MembershipManagement.MembershipManagement_yein;
import diary.DiaryMain;
import game.GameManagement;
import houseHoldAccount.HwangMiniProject;
import inventory.InventoryManagement;
import movie.MovieManager;
import music.MusicMain;
import music.MusicManagement;
import tv.TVProgramManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 프로그램 선택 ===");
            System.out.println("1. 최현태 - 영화 관리 시스템");
            System.out.println("2. 황민우 - 가계부 관리 시스템");
            System.out.println("3. 최지훈 - TV 프로그램 관리 시스템");
            System.out.println("4. 황지민 - 일기 관리 시스템");
            System.out.println("5. 김예인 - 회원 관리 시스템");
            System.out.println("6. 박하은 - 게임 관리 시스템");
            System.out.println("7. 최호경 - 재고 관리 시스템");
            System.out.println("8. 최진실 - 음악 관리 시스템");
            System.out.println("X. 종료");
            System.out.print("선택: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    new MovieManager(scanner).start();
                    break;

                case "2":
                	HwangMiniProject.hwangMenu();
                    break;

                case "3":
                    new TVProgramManagement(scanner).start();
                    break;

                case "4":
                    DiaryMain.diaryMain();
                    break;

                case "5":
                    MembershipManagement_yein.membershipMain(scanner);
                    break;

                case "6":
                    new GameManagement(scanner).start();
                    break;

                case "7":
                    new InventoryManagement().run();
                    break;

                case "8":
                    new MusicMain().showMenu(scanner);
                    break;
                    
                case "X":
                    return;

                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }
}