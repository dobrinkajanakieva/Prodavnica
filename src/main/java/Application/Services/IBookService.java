package Application.Services;

import Application.Models.Book;
import Application.Models.Category;
import Application.Models.Exceptions.BookNotFoundException;
import Application.Models.Exceptions.CategoryNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBookService {
    List<Book> findAll();
    List<Book> findAllById(Long categoryId);
    Book findById(Long id);
    Book saveBook(String name, Integer numberOfCopies, MultipartFile image, Long categoryId) throws IOException;
    Book saveBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteById(Long id);
    boolean exists(Long id);
}

