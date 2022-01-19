package grzybo.evrynote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FolderDTO {

    private Long id;
    private String title;
    private Long authorId;
}
