package Application.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(unique=true)
    private String username;

    @NotNull
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) { this.username=username; }
    public void setPassword(String password) { this.password=password; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
}
