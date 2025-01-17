import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DiaryValidation {
    // Diary 날짜 유효성 검사
    public static void isDiaryDateValid(String strDate) throws
            DiaryException.DateFormatMismatchException,
            DiaryException.InvalidDateException,
            DiaryException.ExceedsAllowedDateException {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            String regex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|3[01])$";
            if (!Pattern.matches(regex, strDate)) {
                throw new DiaryException.DateFormatMismatchException("날짜 형식이 일치하지 않습니다. 다시 입력해주세요. (Ex. 2025-01-16)");
            }

            throw new DiaryException.InvalidDateException("유효하지 않은 날짜입니다. 다시 입력해주세요.");
        }

        // 오늘 이전 날짜인지 체크
        LocalDate today = LocalDate.now();
        if (localDate.isAfter(today)) {
            throw new DiaryException.ExceedsAllowedDateException("오늘이나 이전 날짜만 가능합니다. 다시 입력해주세요.");
        }
    }
}
