package diary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DiaryManagement {
    List<Diary> diaries = new ArrayList<>();
    Long diaryId = 0L;

    // id를 통해 Diary 객체 가져오기
    public Diary getDiaryById(Long id) {
        return diaries.stream()
                .filter(diary -> diary.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Diary 생성
    public void createDiary(Scanner sc) {
        System.out.println("\n==== 일기 작성 ====");

        LocalDate diaryDate = null;
        System.out.println("날짜를 입력해주세요 (형식: 2025-01-16)");
        while (diaryDate == null) {
            System.out.print("> ");
            String inputDate = sc.nextLine();

            try {
                DiaryValidation.isDiaryDateValid(inputDate);
                diaryDate = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
            } catch (DiaryException.DateFormatMismatchException |
                    DiaryException.InvalidDateException |
                    DiaryException.ExceedsAllowedDateException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("제목을 입력하세요");
        System.out.print("> ");
        String title = sc.nextLine();

        System.out.println("내용을 입력하세요");
        System.out.print("> ");
        String content = sc.nextLine();

        System.out.println("해시 태그를 입력하세요 (형식: 행복, 콘서트)");
        System.out.print("> ");
        String hashTagStr = sc.nextLine();

        List<String> hashTags = new ArrayList<>();
        List<String> hashTagList = Arrays.asList(hashTagStr);
        hashTagList.stream()
                .flatMap(str -> {
                    String[] hashTagArr = str.split(", ");
                    return Arrays.stream(hashTagArr);
                })
                .forEach(hashTag -> hashTags.add(hashTag));

        Diary newDiary = new Diary(diaryId++, title, content, diaryDate, hashTags);
        diaries.add(newDiary);

        System.out.println("\n==== 일기가 저장되었습니다. ====");
    }

    // Diary 리스트 출력
    public void printDiaryList() {
        if (diaries.isEmpty()) {
            System.out.println("**일기가 없습니다.**");
            return;
        }

        System.out.println("\n[id]\t[날짜]\t[제목]");
        diaries.forEach(System.out::println);
    }

    // ID를 통해 Diary 상세 내용 출력
    public void printDiaryById(Scanner sc) {
        if (diaries.isEmpty()) {
            System.out.println("**일기가 없습니다.**");
            return;
        }

        System.out.println("\n조회하고 싶은 일기의 id를 입력해주세요.");
        System.out.println("(일기 id 조회는 \"-1\"를 입력하세요.)");

        Long inputId = null;
        while (inputId == null) {
            System.out.print("> ");
            try {
                String choice = sc.nextLine();
                if (choice.equals("-1")) {
                    printDiaryList();
                    System.out.println();
                } else {
                    inputId = Long.parseLong(choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. id를 입려해주세요.");
                System.out.println("(일기 id 조회는 \"-1\"를 입력하세요.)");
            }
        }

        Diary selectedDiary = null;
        while (selectedDiary == null) {
            selectedDiary = getDiaryById(inputId);

            if (selectedDiary == null) {
                System.out.println("일치하는 id가 없습니다. 다시 입력해주세요.");
            }
        }

        System.out.println(selectedDiary);
        System.out.println(selectedDiary.getContent());

        StringBuilder sb = new StringBuilder();
        selectedDiary.getHashTags().stream()
                .forEach(hashTag -> sb.append("#" + hashTag));
        System.out.println(sb);
    }

    // ID를 통한 Diary 삭제
    public void deleteDiaryById(Scanner sc) {
        if (diaries.isEmpty()) {
            System.out.println("**일기가 없습니다.**");
            return;
        }

        System.out.println("\n삭제하고 싶은 일기의 id를 입력하세요.");
        System.out.println("(일기 id 조회는 \"-1\"를 입력하세요.)");

        Long inputId = null;
        while (inputId == null) {
            System.out.print("> ");
            try {
                String choice = sc.nextLine();
                if (choice.equals("-1")) {
                    printDiaryList();
                } else {
                    inputId = Long.parseLong(choice);

                    if (getDiaryById(inputId) == null) {
                        System.out.println("일치하는 id가 없습니다. 다시 입력해주세요.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. id를 입려해주세요.");
                System.out.println("(일기 id 조회는 \"-1\"를 입력하세요.)");
            }
        }

        Long finalInputId = inputId;
        diaries.removeIf(diary -> diary.getId() == finalInputId);
    }

    // Diary 수정
    public void updateDiary(Scanner sc) {
        if (diaries.isEmpty()) {
            System.out.println("**일기가 없습니다.**");
            return;
        }

        System.out.println("\n수정하고 싶은 일기의 id를 입력하세요.");
        System.out.println("(일기 id 조회는 \"-1\"를 입력하세요.)");

        Long inputId = null;
        while (inputId == null) {
            System.out.print("> ");
            try {
                String choice = sc.nextLine();
                if (choice.equals("-1")) {
                    printDiaryList();
                } else {
                    inputId = Long.parseLong(choice);

                    if (getDiaryById(inputId) == null) {
                        System.out.println("일치하는 id가 없습니다. 다시 입력해주세요.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. id를 입려해주세요.");
                System.out.println("(일기 id 조회는 \"-1\"를 입력하세요.)");
            }
        }

        System.out.println("제목을 입력하세요");
        System.out.print("> ");
        String title = sc.nextLine();

        System.out.println("내용을 입력하세요");
        System.out.print("> ");
        String content = sc.nextLine();

        System.out.println("해시 태그를 입력하세요 (형식: 행복, 콘서트)");
        System.out.print("> ");
        String hashTagStr = sc.nextLine();

        List<String> hashTags = new ArrayList<>();
        List<String> hashTagList = Arrays.asList(hashTagStr);
        hashTagList.stream()
                .flatMap(str -> {
                    String[] hashTagArr = str.split(", ");
                    return Arrays.stream(hashTagArr);
                })
                .forEach(hashTag -> hashTags.add(hashTag));

        Diary diary = getDiaryById(inputId);
        diary.setTitle(title);
        diary.setContent(content);
        diary.setHashTags(hashTags);
        diary.setModifiedDate(LocalDate.now());

        System.out.println("일기가 수정되었습니다.");
    }

    // 해시태그를 통한 Diary 검색
    public void searchDiaryByHashTag(Scanner sc) {
        if (diaries.isEmpty()) {
            System.out.println("**일기가 없습니다.**");
            return;
        }

        System.out.println("\n검색할 해시태그를 입력하세요.");
        System.out.print("> ");
        String searchTag = sc.nextLine().trim();

        List<Diary> matchingDiaries = diaries.stream()
                .filter(diary -> diary.getHashTags().stream()
                        .anyMatch(tag -> tag.equalsIgnoreCase(searchTag)))
                .toList();

        if (matchingDiaries.isEmpty()) {
            System.out.println("일치하는 해시태그를 가진 일기가 없습니다.");
        } else {
            System.out.println("\n==== 검색 결과 ====");
            matchingDiaries.forEach(diary -> {
                System.out.println(diary);
            });
        }
    }
}
