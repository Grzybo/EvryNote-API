package grzybo.evrynote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class NoteDTO {

    private Long id;
    private String title;
    private String body;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Long folderID;
    private Long authorID;
    private List<Long> usersIdSharedTo;

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", folderID=" + folderID +
                ", authorID=" + authorID +
                ", usersIdSharedTo=" + usersIdSharedTo +
                '}';
    }
}
