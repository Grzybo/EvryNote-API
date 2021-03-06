package grzybo.evrynote.service;

import grzybo.evrynote.model.Note;
import grzybo.evrynote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private static final int SIZE = 10;
    private final NoteRepository noteRepo;

    @Autowired
    public NoteService(NoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<Note> getAll(){return noteRepo.findAll();}

    public List<Note> getAllNotesByUser(){return noteRepo.findAll();}

    @Cacheable(cacheNames = "PageOfNotes")
    public List<Note> getAllPageable(int page, Sort.Direction sort){return noteRepo.findAllNotes(PageRequest.of(page,SIZE, Sort.by(sort, "title")));}

    public List<Note> getAllPageableByAuthor(int page, Sort.Direction sort, Long authorId){
        return noteRepo.findAllNotesByAuthor(PageRequest.of(page,SIZE, Sort.by(sort, "title")), authorId);
    }

    public List<Note> getAllInFolder(Long folderId){
        return noteRepo.findAllNotesInFolder(folderId);
    }

    public Note addNote(Note note) {
        note.toString();
        return noteRepo.save(new Note(note));}

    @Cacheable(cacheNames = "SingleNote", key = "#id")
    public Optional<Note> getByID(Long id){return noteRepo.findById(id);}

    @CacheEvict(cacheNames = "SingleNote")
    public void deleteByID(Long id){noteRepo.deleteById(id);}

    @CachePut(cacheNames = "SingleNote", key = "#result.id")
    public Note updateByID(Long id, Note newNote){
        Note note = getByID(id).get();
        note.update(newNote);
        return noteRepo.save(note);
    }

}
