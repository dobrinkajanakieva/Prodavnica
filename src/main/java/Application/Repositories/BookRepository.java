package Application.Repositories;

import Application.Models.Book;
import Application.Models.Category;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements IBookRepository {
    private HashMap<Long, Book> books;
    private Long counter;

    @PostConstruct
    public void init() throws IOException {
        this.counter = 0L;
        this.books = new HashMap<>();
        Long id = this.generateKey();
        Path path1 = Paths.get("src/main/resources/static/images/book-cover1.jpg");
        String name1 = "book-cover1.jpg";
        String originalFileName1 = "book-cover1.jpg";
        String contentType1 = "image/jpg";
        byte[] content1 = null;
        content1 = Files.readAllBytes(path1);
        MultipartFile image1 = new MockMultipartFile(name1,
                originalFileName1, contentType1, content1);
        this.books.put(id, new Book(id, "Drama Book", 34,
                new Category(null, "Drama", "Dramatic books."), image1));
        id = this.generateKey();
        Path path2 = Paths.get("src/main/resources/static/images/book-cover2.jpg");
        String name2 = "book-cover1.jpg";
        String originalFileName2 = "book-cover1.jpg";
        String contentType2 = "image/jpg";
        byte[] content2 = null;
        content2 = Files.readAllBytes(path2);
        MultipartFile image2 = new MockMultipartFile(name2,
                originalFileName2, contentType2, content2);
        this.books.put(id, new Book(id,"Adventure Book",50,
                new Category(null, "Adventure", "Adventurous books."), image2));
    }

    public List<Book> findAll() {
        return new ArrayList<Book>(this.books.values());
    }

    public List<Book> findAllByCategoryId(Long categoryId) {
        return this.books.values()
                .stream()
                .filter(item -> item.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(this.books.get(id));
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(this.generateKey());
        }
        this.books.put(book.getId(), book);
        return book;
    }

    public void deleteById(Long id) {
        this.books.remove(id);
    }

    private Long generateKey() {
        return ++counter;
    }
}
