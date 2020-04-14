package Application.Repositories;
import Application.Models.Book;
import Application.Models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface IBookRepository {
    List<Book> findAll();
    List<Book> findAllByCategoryId(Long categoryId);
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}

interface ICategoryRepository {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
    void deleteAll();
}
