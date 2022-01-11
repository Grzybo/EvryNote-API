package grzybo.evrynote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NoteDTO {

    private Long id;
    private String title;
    private String body;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Long authorID;
}
