package tv;

import java.util.*;

public class TVProgramManagement {
    private List<TVProgram> programs = new ArrayList<>();
    private Set<String> programIds = new HashSet<>();

    public void addProgram(Scanner scanner) {
        System.out.print("프로그램 ID: ");
        String id = scanner.nextLine();

        // 프로그램 ID 중복 체크
        if (programIds.contains(id)) {
            System.out.println("이미 등록된 프로그램 ID입니다.");
            return;
        }

        System.out.print("프로그램 제목: ");
        String title = scanner.nextLine();

        System.out.print("장르: ");
        String genre = scanner.nextLine();

        System.out.print("방영 연도: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // 버퍼 정리

        // tv.TVProgram 객체 생성 및 등록
        TVProgram program = new TVProgram(id, title, genre, year);
        programs.add(program);
        programIds.add(id);
        System.out.println("프로그램이 등록되었습니다: " + program);
    }

    /**
     * 전체 TV 프로그램 목록 조회
     */
    public void listPrograms() {
        if (programs.isEmpty()) {
            System.out.println("등록된 프로그램이 없습니다.");
            return;
        }
        // 모든 프로그램 출력
        programs.forEach(System.out::println);
    }

    /**
     * TV 프로그램 검색
     * 제목 또는 장르에 사용자가 입력한 키워드가 포함된 프로그램을 검색
     */
    public void searchPrograms(Scanner scanner) {
        System.out.print("검색어를 입력하세요 (제목/장르): ");
        String keyword = scanner.nextLine().toLowerCase(); // 키워드를 소문자로 변환하여 검색
        boolean found = false;

        // 프로그램 목록에서 키워드와 일치하는 제목 또는 장르 찾기
        for (TVProgram program : programs) {
            if (program.getTitle().toLowerCase().contains(keyword) ||
                    program.getGenre().toLowerCase().contains(keyword)) {
                System.out.println(program); // 검색된 프로그램 출력
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    /**
     * TV 프로그램 삭제
     * 사용자로부터 프로그램 ID를 입력받아 삭제
     */
    public void deleteProgram(Scanner scanner) {
        System.out.print("삭제할 프로그램 ID를 입력하세요: ");
        String id = scanner.nextLine();

        // 프로그램 ID에 해당하는 프로그램 삭제
        Iterator<TVProgram> iterator = programs.iterator();
        while (iterator.hasNext()) {
            TVProgram program = iterator.next();
            if (program.getId().equals(id)) {
                iterator.remove();
                programIds.remove(id);
                System.out.println("프로그램이 삭제되었습니다: " + program);
                return;
            }
        }
        System.out.println("해당 ID의 프로그램을 찾을 수 없습니다.");
    }
}
