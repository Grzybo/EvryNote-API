package grzybo.evrynote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "author")
    private UserModel author;

    public Note() {}

    // nowy obiekt
    public Note(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.created = LocalDateTime.now();
    }

    // update
    public Note(Long id, Note note) {
        this.id = id;
        this.title = note.title;
        this.body = note.body;
        this.author = note.author;
    }
}
