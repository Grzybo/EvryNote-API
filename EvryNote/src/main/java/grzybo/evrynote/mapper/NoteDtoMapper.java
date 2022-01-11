package grzybo.evrynote.mapper;

import grzybo.evrynote.dto.NoteDTO;
import grzybo.evrynote.model.Note;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteDtoMapper {

    public static List<NoteDTO> mapToNoteDtos(List<Note> notes) {
        return notes.stream().map(note -> mapToNoteDto(note)).collect(Collectors.toList());
    }

    public static NoteDTO mapToNoteDto(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .body(note.getBody())
                .created(note.getCreated())
                .modified(note.getModified())
                .authorID(note.getAuthor().getId())
                .build();
    }
}
