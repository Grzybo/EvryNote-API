package grzybo.evrynote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "author")
    private UserModel author;

    public Note() {}

    public Note(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Note(Long id, Note note) {
        this.id = id;
        this.title = note.title;
        this.body = note.body;
        this.author = note.author;
    }


    public void UpdateNote(Note note){
        this.title = note.title;
        this.body = note.body;
    }
}
