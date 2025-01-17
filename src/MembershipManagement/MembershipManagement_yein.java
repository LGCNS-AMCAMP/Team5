package MembershipManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MembershipManagement_yein {
	private static MembershipManagement mem = new MembershipManagement();
	private static void printMMM() {
		System.out.println("\n회원관리: ");
		System.out.println("1. 로그인");
		System.out.println("2. 아이디 찾기");
		System.out.println("3. 비밀번호 찾기");
		System.out.println("4. 회원가입");
		System.out.println("5. 종료");
		System.out.println("0. 관리자 모드");
		System.out.print("메뉴를 선택하세요: ");
	}
	public static void membershipMain(Scanner scanner) {
		while(true) {
			printMMM();
			int choice = scanner.nextInt();
			scanner.nextLine();	
			switch(choice) {
			case 1:
				mem.yein_login(scanner);
				break;
			case 2:
				mem.yein_findId(scanner);
				break;
			case 3:
				mem.yein_findPw(scanner);
				break;
			case 4:
				mem.yein_register(scanner);
				break;
			case 0:
				mem.yein_devmode(scanner);
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				System.out.print("처음으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
			}
		}
	}
}

class MembershipManagement {
	private Map<String, String> members = new HashMap<>();
	private Map<String, String[]> membersInfo = new HashMap<>();
	private List<String[]> accessLog = new ArrayList<String[]>();
	private Map<String, Integer> memberProgress = new HashMap<>();
	private String masterKey = "master";
	
	private static void devMenu() {
		System.out.println("\n관리자 모드");
		System.out.println("1. 접속이력 확인");
		System.out.println("2. 회원정보 조회");
		System.out.println("0. 메인화면");
		System.out.print("메뉴를 선택하세요: ");
	}
	private static void userMenu() {
		System.out.println("\n로그인 성공");
		System.out.println("1. 진행");
		System.out.println("2. 회원정보 조회");
		System.out.println("3. 회원탈퇴");
		System.out.println("0. 메인화면");
		System.out.print("메뉴를 선택하세요: ");
	}
	
	// 로그 찍기에 사용될 함수
	private void logAction(String id, String action) {
        String[] log = {id, action, LocalDateTime.now().toString()};
        accessLog.add(log);
    }
	
	// 로그인 이후에 사용될 함수
	private void show_mypage(String id, Scanner scanner) {
		boolean continueLoop = true;
		while(continueLoop) {
			userMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			continueLoop = select_mypage(id, choice, scanner);
		}
	}
	private boolean select_mypage(String id, int choice, Scanner scanner) {
		switch(choice) {
		case 1: { // 진행
			System.out.println("엔터를 눌러 작업을 진행합니다.");
			scanner.nextLine();
			int currentProgress = memberProgress.get(id);
			 if (currentProgress < 100) {
		            memberProgress.put(id, Math.min(currentProgress + 10, 100));
		            System.out.println(id + "님의 진행률이 " + memberProgress.get(id) + "%로 업데이트되었습니다.");
		        } else {
		            System.out.println(id + "님의 진행률은 이미 100%입니다.");
		        }
			 logAction(id, "진행");
			System.out.print("마이페이지 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
			break;
		}
		case 2: { // 회원정보조회
			String[] info = membersInfo.get(id);
            if (info != null) {
            	System.out.println("회원 ID: " + id + ", 비밀번호: " + members.get(id));
                System.out.println("이름: " + info[0] + ", 생년월일: " + info[1] + ", 본인확인질문: " + info[2] + ", 답변: " + info[3]);
                System.out.println("진행도: " + memberProgress.get(id));
                System.out.println("--------------------------");
                logAction(id, "회원정보조회");
            }
            // 2번 종료
            System.out.print("마이페이지 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
			break;
		}
		case 3: { // 회원탈퇴
			System.out.print("정말로 탈퇴를 원하시면 [탈퇴]라고 입력해주세요: ");
			String deleteCheck = scanner.nextLine().trim();
			if (deleteCheck.equals("탈퇴")) {
				members.remove(id);
				membersInfo.remove(id);
				memberProgress.remove(id);
				logAction(id, "회원 탈퇴");
				// 탈퇴시 종료
				System.out.println("회원탈퇴가 완료되었습니다.");
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				return false;
			}
			else {
				// 탈퇴 실패(?) 시 마이페이지로
				System.out.print("마이페이지 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
			}
			break;
		}
		case 0: { //메인화면으로
			System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
			return false;
			}
		}
		
		return true;
	}
	
	// 관리자모드에 사용될 함수
	private void show_devpage(Scanner scanner) {
		boolean continueLoop = true;
		while(continueLoop) {
			devMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			continueLoop = select_devpage(choice, scanner);
		}
	}
	private boolean select_devpage(int choice, Scanner scanner) {
		switch(choice) {
		case 1:{
			if (accessLog.isEmpty()) {
				System.out.println("접속 이력이 없습니다.");
				System.out.print("관리자 모드 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				break;
			}
			System.out.println("접속 이력을 확인할 회원 ID를 입력세요. master를 입력하면 모든 회원의 기록을 불러옵니다.");
			System.out.print("ID: ");
			String id = scanner.nextLine().trim();
			boolean found = false;
			if (id.equals(masterKey)) {
				System.out.println("모든 접속 기록을 불러옵니다.");
				for (String[] log : accessLog) {
					System.out.printf("[%s] ID: %s (%s)\n", log[2], log[0], log[1]);
				}
			}
			else {
				System.out.println(id+"의 접속기록");
			    for (String[] log : accessLog) {
			        if (log[0].equals(id)) {
			            System.out.printf("[%s] %s\n", log[2], log[1]);
			            found = true;
			        }}
			    if (!found) {
			        System.out.println("해당 회원의 접속 이력이 없습니다.");
			    }
			}
			System.out.print("관리자 모드 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
		    break;
		    }
		case 2: {// 회원정보 조회
			if (members.isEmpty()) {
				System.out.println("회원 정보가 없습니다.");
				System.out.print("관리자 모드 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
			}
			else {
				System.out.println("회원 정보를 확인할 회원 ID를 입력세요. master를 입력하면 모든 회원의 정보를 불러옵니다.");
				System.out.print("ID: ");
				String id = scanner.nextLine().trim();
				boolean found = false;
				if (id.equals(masterKey)) {
					// 모든회원 정보 조회
					System.out.println("모든 회원의 정보를 출력합니다");
					for (Map.Entry<String, String> entry : members.entrySet()) {
			            String memberId = entry.getKey();
			            String pw = entry.getValue();
			            String[] info = membersInfo.get(memberId);
			            if (info != null) {
			                System.out.println("회원 ID: " + memberId + ", 비밀번호: " + pw);
			                System.out.println("이름: " + info[0] + ", 생년월일: " + info[1] + ", 본인확인질문: " + info[2] + ", 답변: " + info[3]);
			                System.out.println("--------------------------");
			            }
			        }
				}
				else {
			        if (members.containsKey(id)) {
			            String[] info = membersInfo.get(id);
			            System.out.println("회원 ID: " + id + ", 비밀번호: " + members.get(id));
			            System.out.println("이름: " + info[0] + ", 생년월일: " + info[1] + ", 본인확인질문: " + info[2] + ", 답변: " + info[3]);
			            System.out.println("--------------------------");
			        }
			        else {
			        	System.out.println("해당 ID는 존재하지 않습니다.");
			        }
				}
			} // 비어있지않을때... 닫기
				System.out.print("관리자 모드 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				break;
				}
			case 0:{
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				return false;
				}
			default:{
				System.out.println("잘못된 입력입니다.");
				System.out.print("관리자 모드 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				break;
				}
		}
		return true;
	}
	
	// 1. 로그인
	public void yein_login(Scanner scanner) {
		System.out.print("ID: "); // 로그인 - 아이디 입력받기
		String id = scanner.nextLine().trim();
		if(members.containsKey(id)) { // 존재하는 아이디일 경우
			System.out.print("PASSWORD: "); // 비밀번호 입력 받음
			String pw = scanner.nextLine().trim();
			if (members.get(id).equals(pw)) { // 비밀번호 일치
				System.out.printf("%s로 로그인하였습니다.\n", id);
				logAction(id, "로그인 성공");
				show_mypage(id, scanner);
			} //로그인성공 닫기
			// 비밀번호 틀림
			else {
				logAction(id, "로그인 실패");
				System.out.println("비밀번호 오류");
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
				return;
			}
		}
		// 존재하지 않는 아이디
		else {
			System.out.println("등록된 회원ID가 없습니다.");
			System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
			return;
		}
	}
	// 2. 아이디 찾기
	public void yein_findId(Scanner scanner) {
		System.out.println("아이디 찾기");
		// 이름과 생년월일을 통해 아이디를 찾는다
		System.out.print("이름: ");
		String name = scanner.nextLine().trim();
		System.out.print("생년월일: ");
		String birth = scanner.nextLine().trim();
		boolean found = false;
		for (Map.Entry<String, String[]> entry : membersInfo.entrySet()) {
	        String[] info = entry.getValue();
	        if (info[0].equals(name) && info[1].equals(birth)) {
	        	String id = entry.getKey();
	            System.out.printf("%s님의 아이디는 '%s'입니다.\n", name, id);
	            found = true;
	            logAction(id, "아이디 찾기 성공");
				// 아이디 찾기 종료
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
	            scanner.nextLine();
	            break;
	        }
	    }
		if (!found) {
			System.out.println("일치하는 회원 정보가 없습니다.");
			System.out.println("추가적인 도움이 필요하다면, 관리자에게 문의하세요. contact@example.com");
			// 아이디 찾기 종료
			System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
		}
	}
	// 3. 비밀번호 찾기
	public void yein_findPw(Scanner scanner) {
		System.out.println("비밀번호 찾기");
		System.out.print("ID: ");
		String id = scanner.nextLine().trim();
		if (!members.containsKey(id)) {
			System.out.println("일치하는 회원 정보가 없습니다.");
			System.out.println("추가적인 도움이 필요하다면, 관리자에게 문의하세요. contact@example.com");
			// 비번찾기 종료
			System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
		}
		else {
			System.out.printf("[본인확인질문] %s: ", membersInfo.get(id)[2]);
			String ans = scanner.nextLine().trim();
			if (membersInfo.get(id)[3].equals(ans)) {
				logAction(id, "비밀번호 찾기 성공");
				System.out.printf("%s님의 비밀번호는 %s입니다.", id, members.get(id));
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
			}
			else {
				logAction(id, "비밀번호 찾기 실패");
				System.out.println("잘못된 정보입니다.");
				System.out.println("추가적인 도움이 필요하다면, 관리자에게 문의하세요. contact@example.com");
				System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
				scanner.nextLine();
			}
		}
		
	}
	// 4. 회원가입
	public void yein_register(Scanner scanner) {
		System.out.println("회원 가입");
		String id = "master";
		while(true) {
			System.out.print("ID: ");
			id = scanner.nextLine().trim();
			 if (id.isEmpty()) {
		            System.out.println("ID는 공백일 수 없습니다.");
		            continue;
		     }
			if (members.containsKey(id)) {
				System.out.println("이미 존재하는 ID입니다.");
				continue;
			}
			else if (id.equals("master")) { System.out.println("사용할 수 없는 ID입니다."); continue; }
			else { break; }
		}
		System.out.print("PW: ");
		String pw = scanner.nextLine().trim();
//		System.out.print("PW 확인: ");
//		String checkPw = scanner.next();
		
		System.out.println("아이디/비밀번호 찾기를 위해 개인정보를 수집합니다");
		System.out.print("이름: ");
		String name = scanner.nextLine().trim();
		System.out.print("생년월일: ");
		String birth = scanner.nextLine().trim();
		System.out.print("본인확인질문: ");
		String question = scanner.nextLine().trim();
		System.out.print("본인확인질문 정답: ");
		String answer = scanner.nextLine().trim();
		
		String[] info = {name, birth, question, answer};
		
		members.put(id, pw);
		membersInfo.put(id, info);
		memberProgress.put(id, 0);
		logAction(id, "회원가입");
		System.out.printf("%s님, 회원가입을 환영합니다.\n", id);
		System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
		scanner.nextLine();
	}
	// 5. 관리자 모드
	public void yein_devmode(Scanner scanner) {
		System.out.print("관리자용 비밀번호를 입력하세요: ");
		String key = scanner.nextLine().trim();
		if (key.equals(masterKey)) {
			 show_devpage(scanner);
		}
		else {
			System.out.println("오류");
			System.out.print("메인 화면으로 돌아가기 위해 엔터를 눌러주세요.");
			scanner.nextLine();
		}
		}//관리자모드 닫기
}