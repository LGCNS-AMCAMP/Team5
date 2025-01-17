package houseHoldAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseHoldAccount {
	//가계부 입출금 내역
	private List<History> histories = new ArrayList<>();
	
	//현재 금액
	private int curMoney = 0;
	
	//수입
	public void addMoney(Scanner scanner) {
		System.out.println("날짜 입력 예시: 2025-01-01");
		System.out.print("날짜: ");
		String date = scanner.nextLine();
		System.out.print("항목: ");
		String source = scanner.nextLine();
		System.out.print("금액: ");
		int money = scanner.nextInt();
		System.out.print("메모: ");
		scanner.nextLine();
		String memo = scanner.nextLine();
		
		History history = new History(date, source, money, "수입", memo);
		histories.add(history);
		System.out.println("등록되었습니다." + history);
		
		curMoney += money;
	}
	
	//지출
	public void subMoney(Scanner scanner) {
		System.out.println("날짜 입력 예시: 2025-01-01");
		System.out.print("날짜: ");
		String date = scanner.nextLine();
		System.out.print("항목: ");
		String source = scanner.nextLine();
		System.out.print("금액: ");
		int money = scanner.nextInt();
		if(money > curMoney) {
			System.out.println("지출 금액이 현재 금액보다 큽니다");
			return;
		}
		System.out.print("메모: ");
		String memo = scanner.nextLine();
		
		History history = new History(date, source, money, "지출", memo);
		histories.add(history);
		System.out.println("등록되었습니다." + history);
		
		curMoney -= money;
	}
	
	//전체 내역 조회
	public void listHistory() {
		if(histories.isEmpty()) {
			System.out.println("가계부 내역이 없습니다. 현재 잔액 0원입니다.");
			return;
		}
		histories.forEach(System.out::println);
	}
	
	//현재 재산 확인
	public void checkCurMoney() {
		System.out.println("현재 잔액은 " + curMoney + "원 입니다.");
	}
	
	//월별 내역 확인
	public void monthMoney(Scanner scanner) {
		System.out.print("검색하려는 년도를 입력해 주세요: ");
		int year = scanner.nextInt();
		System.out.print("검색하려는 달을 입력해 주세요: ");
		int month = scanner.nextInt();
		if(month>12 || month <1) {
			System.out.println("정확한 달을 기입해주십시오");
			return;
		}
		
		
		boolean found = false;
		for(History history : histories) {
			if(year == Integer.parseInt(history.date.split("-")[0]) || month == Integer.parseInt(history.date.split("-")[1])) {
				System.out.println(history);
				found =true;
			}
		}
		if(!found) {
			System.out.println(month + "달의 내역이 존재하지 않습니다.");
		}
	}
	
	//항목, 메모 내역 기반 검색
	public void searchMemo(Scanner scanner) {
		System.out.print("검색어를 입력하세요: ");
		String keyword = scanner.nextLine();
		keyword = keyword.trim().toLowerCase();
		
		boolean found = false;
		for (History history : histories) {
			if (history.getMemo().trim().toLowerCase().contains(keyword) || history.getSource().trim().toLowerCase().contains(keyword)) {
				System.out.println(history);
				found = true;
			}
		}
		if(!found) {
			System.out.println(keyword + "를 포함하는 내역이 존재하지 않습니다.");
		}
	}
}
