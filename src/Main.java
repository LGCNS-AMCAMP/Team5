import movie.MovieManager;

import java.util.*;

import houseHoldAccount.HwangMiniProject;
import tv.TVProgramManagement;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 프로그램 선택 ===");
            System.out.println("1. 최현태 - 회원관리");
            System.out.println("2. 황민우 - 가계부");
            System.out.println("3. 최지훈 - TV 프로그램 관리");
            System.out.println("X. 종료");
            System.out.print("선택: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    MovieManager movieManager = new MovieManager(scanner);
                    movieManager.start();
                    break;

                case "2":
                	HwangMiniProject.hwangMenu();
                    break;

                case "3":
                    TVProgramManagement tvManager = new TVProgramManagement(scanner);
                    tvManager.start();
                    break;

                case "X":
                    return;

                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }
}