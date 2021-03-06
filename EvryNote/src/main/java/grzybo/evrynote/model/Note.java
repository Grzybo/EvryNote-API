package grzybo.evrynote.model;

import grzybo.evrynote.dto.NoteDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
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

    @Column(name = "modified_date")
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "fk_author_id")
    private UserModel author;

    @ManyToOne
    @JoinColumn(name = "fk_folder_id")
    private Folder folder;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_NOTES",
            joinColumns = { @JoinColumn(name = "fk_note_id") },
            inverseJoinColumns = { @JoinColumn(name = "fk_user_id") })
    private List<UserModel> usersSharedTo;

    public Note() {}

    // nowy obiekt - konstruktor kopiujący
    public Note(Note note) {
        this.id = note.id;
        this.title = note.title;
        this.body = note.body;
        this.author = note.author;
        this.folder = note.folder;
        this.usersSharedTo = note.getUsersSharedTo();
        this.created = this.modified = LocalDateTime.now();
    }

    public Note(Long id, String title, String body, LocalDateTime created, LocalDateTime modified, UserModel author, Folder folder, List<UserModel> usersSharedTo) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.created = created;
        this.modified = modified;
        this.author = author;
        this.folder = folder;
        this.usersSharedTo = usersSharedTo;
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

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", author=" + author +
                ", folder=" + folder +
                ", usersSharedTo=" + usersSharedTo +
                '}';
    }
}
