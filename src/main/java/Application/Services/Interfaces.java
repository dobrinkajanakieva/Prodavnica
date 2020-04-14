package Application.Services;

import Application.Models.Book;
import Application.Models.Category;
import Application.Models.Exceptions.BookNotFoundException;
import Application.Models.Exceptions.CategoryNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

interface IBookService {
    List<Book> findAll();
    List<Book> findAllByCategoryId(Long categoryId);
    Book findById(Long id);
    Book saveBook(String name, Integer numberOfCopies, MultipartFile image, Long categoryId);
    Book saveBook(Book book);
    Book saveBook(Book book, MultipartFile image);
    Book updateBook(Long id, Book book);
    void deleteById(Long id);
}

interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category manufacturer);
    Category update(Long id, Category category);
    Category updateName(Long id, String name);
    Category updateDescription(Long id, String description);
    void deleteById(Long id);
}
