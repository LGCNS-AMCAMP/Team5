package tv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class TVProgram {
    private String id;      // 프로그램 ID
    private String title;   // 프로그램 제목
    private String genre;   // 프로그램 장르
    private int year;       // 프로그램 방영 연도
}
