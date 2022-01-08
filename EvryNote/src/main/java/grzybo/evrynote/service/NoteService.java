package grzybo.evrynote.service;

import grzybo.evrynote.model.Note;
import grzybo.evrynote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private static final int SIZE = 2;
    private final NoteRepository noteRepo;

    @Autowired
    public NoteService(NoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<Note> getAll(){return noteRepo.findAll();}
    public List<Note> getAllNotesByUser(){return noteRepo.findAll();}

    public List<Note> getAllPageable(int page, Sort.Direction sort){return noteRepo.findAllNotes(PageRequest.of(page,SIZE, Sort.by(sort, "title")));}

    public Note addNote(Note note) {return noteRepo.save(note);}
    public Optional<Note> getByID(Long id){return noteRepo.findById(id);}
    public void deleteByID(Long id){noteRepo.deleteById(id);}
    public Note updateByID(Long id, Note note){return noteRepo.save(new Note(id, note));}
}
