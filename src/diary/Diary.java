package diary;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Diary {
    @NonNull private Long id;       // 일기 id
    @NonNull private String title;  // 일기 제목
    @NonNull private String content;    // 일기 내용
    @NonNull private LocalDate diaryDate;   // 일기 날짜
    @NonNull private List<String> hashTags; // 일기 해시태그

    private final LocalDate createDate = LocalDate.now(); // 생성 시점으로 기본값 설정
    private LocalDate modifiedDate = LocalDate.now();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(id).append("]\t");
        sb.append(diaryDate).append("\t");
        sb.append(title).append("\t");
        this.getHashTags().stream()
                .forEach(hashTag -> sb.append("#" + hashTag));
        return sb.toString();
    }
}
