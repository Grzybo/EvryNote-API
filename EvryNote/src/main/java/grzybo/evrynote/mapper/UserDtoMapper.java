package grzybo.evrynote.mapper;

import grzybo.evrynote.dto.NoteDTO;
import grzybo.evrynote.dto.UserDTO;
import grzybo.evrynote.model.Note;
import grzybo.evrynote.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    public static List<UserDTO> mapToUserDtos(List<UserModel> users) {
        return users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
    }

    public static UserDTO mapToUserDto(UserModel user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .build();
    }
}
