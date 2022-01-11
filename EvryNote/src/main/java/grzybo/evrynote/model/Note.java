package grzybo.evrynote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "NOTES")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "created_date")
    private LocalDateTime created;

    @Column(name = "lastmodify_date")
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserModel author;

    public Note() {}

    // nowy obiekt - konstruktor kopiujący
    public Note(Note note) {
        this.id = note.id;
        this.title = note.title;
        this.body = note.body;
        this.author = note.author;
        this.created = LocalDateTime.now();
    }

    // update
    public Note(Long id, Note note) {
        this.id = id;
        this.title = note.title;
        this.body = note.body;
        this.author = note.author;
        this.created = note.created;
        this.modified = LocalDateTime.now();
    }

    public void update(Note note){
        this.id = id;
        if(note.title != null)this.title = note.title;
        if(note.body != null)this.body = note.body;
        if(note.author != null)this.author = note.author;
        if(note.created != null)this.created = note.created;
        this.modified = LocalDateTime.now();
    }

    public Note(String title, String body, LocalDateTime created, LocalDateTime modified, UserModel author) {
        this.title = title;
        this.body = body;
        this.created = created;
        this.modified = modified;
        this.author = author;
    }
}
