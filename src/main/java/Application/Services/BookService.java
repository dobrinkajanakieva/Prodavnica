package Application.Services;

import Application.Models.Book;
import Application.Models.Category;
import Application.Models.Exceptions.BookNotFoundException;
import Application.Repositories.BookRepository;
import Application.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository,
                              CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public List<Book> findAllByCategoryId(Long categoryId) {
        return this.bookRepository.findAllByCategoryId(categoryId);
    }

    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book saveBook(String name, Integer numberOfCopies, MultipartFile image, Long categoryId) {
        Category category = this.categoryService.findById(categoryId);
        Book book = new Book(null, name, numberOfCopies, category
                //, image
        );
        return this.bookRepository.save(book);
    }

    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }

    public Book saveBook(Book book, String image){
        Category category = this.categoryService.findById(book.getCategory().getId());
        book.setCategory(category);
        if (image != null) {
            // book.setImage(image);
        }
        return this.bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book b = this.findById(id);
        Category category = this.categoryService.findById(book.getCategory().getId());
        b.setCategory(category);
        b.setName(book.getName());
        b.setNumberOfCopies(book.getNumberOfCopies());
        //b.setImage(book.getImage());
        return this.bookRepository.save(b);
    }

    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
