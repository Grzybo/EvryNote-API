package grzybo.evrynote.mapper;

import grzybo.evrynote.dto.FolderDTO;
import grzybo.evrynote.dto.NoteDTO;
import grzybo.evrynote.model.Folder;
import grzybo.evrynote.model.Note;

import java.util.List;
import java.util.stream.Collectors;

public class FolderDtoMapper {

    public static List<FolderDTO> mapToFolderDtos(List<Folder> folders) {
        return folders.stream().map(folder -> mapToFolderDto(folder)).collect(Collectors.toList());
    }

    public static FolderDTO mapToFolderDto(Folder folder){
        return FolderDTO.builder()
                .id(folder.getId())
                .title(folder.getTitle())
                .authorId(folder.getAuthor().getId())
                .build();
    }
}
