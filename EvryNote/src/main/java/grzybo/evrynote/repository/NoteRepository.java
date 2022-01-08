package grzybo.evrynote.repository;

import grzybo.evrynote.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("Select n From Note n left join fetch n.author")
    List<Note> findAllNotes(Pageable page);

    //@Query("Select n From Note n WHERE n.author.id =")
    //List<Note> findAllNotesByAuthor(Pageable page, Long id);
}
