package Application.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private LocalDateTime dateCreated;

    private LocalDateTime dateClosed;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    public ShoppingCart() {
        this.books = new ArrayList<>();
    }

    public ShoppingCart(Long id, Status status, LocalDateTime dateCreated, LocalDateTime dateClosed, User user, List<Book> books) {
        this.id = id;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateClosed = dateClosed;
        this.user = user;
        this.books = books;
    }

    public void setId(Long id) { this.id = id; }
    public void setStatus(Status status) { this.status = status; }
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }
    public void setDateClosed(LocalDateTime dateClosed) { this.dateClosed = dateClosed; }
    public void setUser(User user) { this.user = user; }
    public void setBooks(List<Book> books) { this.books = books; }
    public Long getId() { return this.id; }
    public Status getStatus() { return this.status; }
    public LocalDateTime getDateCreated() { return this.dateCreated; }
    public LocalDateTime getDateClosed() { return this.dateClosed; }
    public User getUser() { return this.user; }
    public List<Book> getBooks() { return this.books; }
}
