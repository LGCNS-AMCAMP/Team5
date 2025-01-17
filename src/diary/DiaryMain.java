package diary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DiaryMain {
    // Diary 프로그램 메뉴 출력
    public static void printDiaryMenu() {
        System.out.println("\n==== 일기 프로그램 ====");
        System.out.println("1. 일기 작성");
        System.out.println("2. 일기 리스트 조회");
        System.out.println("3. 일기 상세 조회");
        System.out.println("4. 일기 삭제");
        System.out.println("5. 일기 수정");
        System.out.println("6. 해시태그로 검색");
        System.out.println("7. 일기 프로그램 종료");
        System.out.println("메뉴를 선택하세요");
    }

    // Diary 프로그램 실행
    public static void diaryMain() {
        DiaryManagement dm = new DiaryManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printDiaryMenu();

            int choice = -1;
            while (choice == -1) {
                try {
                    System.out.print("> ");
                    choice = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("메뉴 확인 후, 숫자를 입력해주세요.");
                    sc.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    dm.createDiary(sc);
                    break;
                case 2:
                    dm.printDiaryList();
                    break;
                case 3:
                    dm.printDiaryById(sc);
                    break;
                case 4:
                    dm.deleteDiaryById(sc);
                    break;
                case 5:
                    dm.updateDiary(sc);
                    break;
                case 6:
                    dm.searchDiaryByHashTag(sc);
                    break;
                case 7:
                    System.out.println("일기 프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("메뉴가 잘못 입력되었습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    // public static void main(String[] args) {
    //     diaryMain();
    // }
}