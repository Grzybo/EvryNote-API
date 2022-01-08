package grzybo.evrynote.mapper;

import grzybo.evrynote.dto.NoteDTO;
import grzybo.evrynote.model.Note;

import java.util.List;
import java.util.stream.Collectors;

public abstract class NoteDtoMapper {

    private NoteDtoMapper() {
    }

    public static List<NoteDTO> mapToNoteDtos(List<Note> notes) {
        return notes.stream().map(note -> mapToNoteDto(note)).collect(Collectors.toList());
    }

    private static NoteDTO mapToNoteDto(Note note) {
        return NoteDTO.builder().id(note.getId()).title(note.getTitle()).body(note.getBody()).build();
    }
}
