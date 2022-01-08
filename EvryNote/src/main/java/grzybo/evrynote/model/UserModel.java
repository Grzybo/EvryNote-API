package grzybo.evrynote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    public UserModel(String userName, String password) {
        this.username = userName;
        this.password = password;
        this.enabled = true;
    }

    public UserModel(Long id, UserModel user) {
        this.id = id;
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
    }

    public UserModel() {}
}
