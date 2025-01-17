public class DiaryException {
    // 날짜 형식 불일치 예외
    public static class DateFormatMismatchException extends Exception {
        public DateFormatMismatchException(String message) {
            super(message);
        }
    }

    // 유효하지 않은 날짜 예외
    public static class InvalidDateException extends Exception {
        public InvalidDateException(String message) {
            super(message);
        }
    }

    // 허용되지 않은 날짜 예외 (내일 이후 날짜인 경우)
    public static class ExceedsAllowedDateException extends Exception {
        public ExceedsAllowedDateException(String message) {
            super(message);
        }
    }
}
