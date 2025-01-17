package music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Music {
    private String id;
    private String title;
    private String artist;
    private String genre;
}