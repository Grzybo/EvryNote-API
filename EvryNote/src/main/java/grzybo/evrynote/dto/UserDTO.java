package grzybo.evrynote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
}
