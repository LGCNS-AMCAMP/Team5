import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
class Inventory{
	String name;
	int category;
	int count;
}

class Management{
	private List<Inventory> inventorys = new ArrayList<>();
    private Set<String> nameSet = new HashSet<>();
    
    public void printMenu() {
        System.out.println("\n상품 관리: ");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 조회");
        System.out.println("3. 상품 재고 변경");
        System.out.println("4. 상품 삭제");
        System.out.println("5. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }
	
    public void add(Scanner scanner) {
        while (true) {
            try {
                System.out.print("상품명: ");
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("상품명은 공백일 수 없습니다.");
                    continue;
                }

                if (nameSet.contains(name)) {
                    System.out.printf("%s는 이미 존재하는 상품입니다.%n", name);
                    continue;
                }

                System.out.println("상품 종류: 1.의류 2.식품 3.가전 4.기타");
                System.out.print("상품 종류와 일치하는 번호를 입력해주세요: ");
                int category = Integer.parseInt(scanner.nextLine());

                if (category < 1 || category > 4) {
                    System.out.println("잘못된 상품 종류입니다. 1~4 사이의 숫자를 입력하세요.");
                    continue;
                }

                System.out.print("상품 갯수: ");
                int count = Integer.parseInt(scanner.nextLine());

                if (count <= 0) {
                    System.out.println("상품 갯수는 1개 이상이어야 합니다.");
                    continue;
                }

                inventorys.add(new Inventory(name, category, count));
                nameSet.add(name);
                System.out.printf("%s(이)가 %d개 정상 등록되었습니다.%n", name, count);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                continue;
            }

            System.out.print("상품을 추가로 등록하시겠습니까? (Y/N): ");
            String YorN = scanner.nextLine().trim();

            if (YorN.equalsIgnoreCase("N") || YorN.equalsIgnoreCase("아니요")) {
                System.out.println("상품 추가를 종료합니다.");
                break;
            } else if (!YorN.equalsIgnoreCase("Y") && !YorN.equalsIgnoreCase("예")) {
                System.out.println("잘못된 입력입니다. 상품 추가를 종료합니다.");
                break;
            }
        }
    }
    
    public void search(Scanner scanner) {
        if (inventorys.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        System.out.println("상품 조회 옵션:");
        System.out.println("1. 특정 상품 조회");
        System.out.println("2. 상품 유형별 조회");
        System.out.println("3. 전체 상품 조회");
        System.out.print("원하는 옵션을 선택하세요: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        switch (choice) {
            case 1:
                // 특정 상품 조회
                System.out.print("검색할 상품명을 입력하세요: ");
                String searchName = scanner.nextLine().trim();

                boolean found = false;
                for (Inventory in : inventorys) {
                    if (in.getName().equalsIgnoreCase(searchName)) {
                        String category = "";
                        switch (in.getCategory()) {
                            case 1: category = "의류"; break;
                            case 2: category = "식품"; break;
                            case 3: category = "가전"; break;
                            case 4: category = "기타"; break;
                        }
                        System.out.printf("상품명: %s, 상품 유형: %s, 상품 갯수: %d%n", in.getName(), category, in.getCount());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.printf("'%s'은(는) 존재하지 않습니다.%n", searchName);
                }
                break;

            case 2:
                // 상품 유형별 조회
                System.out.println("상품 유형: 1.의류 2.식품 3.가전 4.기타");
                System.out.print("조회할 상품 유형 번호를 입력하세요: ");
                int categoryChoice = scanner.nextInt();
                scanner.nextLine(); // 버퍼 클리어

                if (categoryChoice < 1 || categoryChoice > 4) {
                    System.out.println("잘못된 상품 유형입니다. 1~4 사이의 숫자를 입력하세요.");
                    return;
                }

                boolean categoryFound = false;
                for (Inventory in : inventorys) {
                    if (in.getCategory() == categoryChoice) {
                        System.out.printf("상품명: %s, 상품 갯수: %d%n", in.getName(), in.getCount());
                        categoryFound = true;
                    }
                }

                if (!categoryFound) {
                    System.out.println("해당 유형의 상품이 없습니다.");
                }
                break;

            case 3:
                // 전체 상품 조회
                for (Inventory in : inventorys) {
                    String category = "";
                    switch (in.getCategory()) {
                        case 1: category = "의류"; break;
                        case 2: category = "식품"; break;
                        case 3: category = "가전"; break;
                        case 4: category = "기타"; break;
                    }
                    System.out.printf("상품명: %s, 상품 유형: %s, 상품 갯수: %d%n", in.getName(), category, in.getCount());
                }
                break;

            default:
                System.out.println("잘못된 입력입니다. 조회를 종료합니다.");
        }
    }

    
    public void change(Scanner scanner) {
        if (inventorys.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        System.out.print("수정할 상품명을 입력하세요: ");
        String searchName = scanner.nextLine().trim();

        boolean found = false;
        for (Inventory in : inventorys) {
            if (in.getName().equalsIgnoreCase(searchName)) {
                found = true;

                System.out.println("1. 상품 갯수 변경");
                System.out.println("2. 상품 유형 변경");
                System.out.print("수정할 항목을 선택하세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 버퍼 클리어

                switch (choice) {
                    case 1:
                        // 상품 갯수 변경
                        System.out.print("새로운 상품 갯수를 입력하세요: ");
                        int newCount = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 클리어

                        if (newCount <= 0) {
                            System.out.println("상품 갯수는 1개 이상이어야 합니다.");
                        } else {
                            inventorys.set(inventorys.indexOf(in), new Inventory(in.getName(), in.getCategory(), newCount));
                            System.out.printf("'%s'의 상품 갯수가 %d로 변경되었습니다.%n", in.getName(), newCount);
                        }
                        break;

                    case 2:
                        // 상품 유형 변경
                        System.out.println("상품 종류: 1.의류 2.식품 3.가전 4.기타");
                        System.out.print("새로운 상품 유형 번호를 입력하세요: ");
                        int newCategory = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 클리어

                        if (newCategory < 1 || newCategory > 4) {
                            System.out.println("잘못된 상품 종류입니다. 1~4 사이의 숫자를 입력하세요.");
                        } else {
                            inventorys.set(inventorys.indexOf(in), new Inventory(in.getName(), newCategory, in.getCount()));
                            System.out.printf("'%s'의 상품 유형이 %d로 변경되었습니다.%n", in.getName(), newCategory);
                        }
                        break;

                    default:
                        System.out.println("잘못된 입력입니다. 상품 상태 변경을 종료합니다.");
                }
                break;
            }
        }

        if (!found) {
            System.out.printf("'%s'은(는) 존재하지 않습니다.%n", searchName);
        }
    }

    
    public void delete(Scanner scanner) {
        if (inventorys.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        System.out.println("1. 전체 상품 삭제");
        System.out.println("2. 특정 상품 삭제");
        System.out.print("삭제 옵션을 선택하세요: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        switch (choice) {
            case 1:
                // 전체 상품 삭제
                System.out.print("정말로 전체 상품을 삭제하시겠습니까? (Y/N): ");
                String confirm = scanner.nextLine().trim();

                if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("예")) {
                    inventorys.clear();
                    nameSet.clear();
                    System.out.println("모든 상품이 삭제되었습니다.");
                } else {
                    System.out.println("전체 상품 삭제가 취소되었습니다.");
                }
                break;

            case 2:
                // 특정 상품 삭제
                System.out.print("삭제할 상품명을 입력하세요: ");
                String searchName = scanner.nextLine().trim();

                boolean found = false;
                for (int i = 0; i < inventorys.size(); i++) {
                    if (inventorys.get(i).getName().equalsIgnoreCase(searchName)) {
                        inventorys.remove(i);
                        nameSet.remove(searchName);
                        System.out.printf("상품명 '%s'이(가) 삭제되었습니다.%n", searchName);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.printf("상품명 '%s'은(는) 존재하지 않습니다.%n", searchName);
                }
                break;

            default:
                System.out.println("잘못된 입력입니다. 삭제를 종료합니다.");
        }
    }

    
}


public class InventoryManagement {
	Management management = new Management();
	Scanner scanner = new Scanner(System.in);
	
	public void run() {
		while(true) {
			management.printMenu();
	            
	        int choice = scanner.nextInt();
	        scanner.nextLine();
	            
	        switch(choice) {
	        case 1:
	        	management.add(scanner);
	            break;
	        case 2:
	        	management.search(scanner);
	            break;
	        case 3:
	        	management.change(scanner);
	            break;
	        case 4:
	        	management.delete(scanner);
	            break;
	        case 5:
	            System.out.println("프로그램을 종료합니다.");
	            scanner.close();
	            return;
	        default:
	            System.out.println("잘못된 입력입니다.");
	        }
	    }
	}

}