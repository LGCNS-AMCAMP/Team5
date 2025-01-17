package houseHoldAccount;

import java.util.Scanner;

public class HwangMiniProject {
	public static void printMenu() {
		System.out.println("\n가계부: ");
        System.out.println("1. 수입");
        System.out.println("2. 지출");
        System.out.println("3. 전체 내역 확인");
        System.out.println("4. 월별 내역 확인");
        System.out.println("5. 현재 재산 확인");
        System.out.println("6. 검색");
        System.out.println("7. 종료");
        System.out.print("메뉴를 선택하세요: ");
	}
	
	public static void hwangMenu() {
		HouseHoldAccount account = new HouseHoldAccount();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
            printMenu();
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
            case 1: 
                account.addMoney(scanner);
                break;
            case 2:
                account.subMoney(scanner);
                break;
            case 3:
                account.listHistory();
                break;
            case 4:
                account.monthMoney(scanner);
                break;
            case 5: 
                account.checkCurMoney();
                break;
            case 6: 
                account.searchMemo(scanner);;
                break;
            case 7:
                System.out.println("가계부를 덮습니다.");
                scanner.close();
                return;
            default:
                System.out.println("잘못된 입력입니다.");
            }
        }
	}
}