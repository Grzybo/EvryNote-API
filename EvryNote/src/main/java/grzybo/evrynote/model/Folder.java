package grzybo.evrynote.model;

import grzybo.evrynote.repository.FolderRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "FOLDERS")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "fk_author_id")
    private UserModel author;

    public Folder() {
    }

    public Folder(Long id, Folder folder) {
        this.id = id;
        this.title = folder.getTitle();
        this.author = folder.getAuthor();
    }
}
