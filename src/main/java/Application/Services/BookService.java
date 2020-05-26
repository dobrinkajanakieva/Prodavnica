package Application.Services;

import Application.Models.Book;
import Application.Models.Category;
import Application.Models.Exceptions.BookNotFoundException;
//import Application.Repositories.BookRepository;
//import Application.Repositories.CategoryRepository;
import Application.Repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ICategoryService categoryService;

//    public BookService(BookRepository bookRepository,
//                              CategoryService categoryService) {
//        this.bookRepository = bookRepository;
//        this.categoryService = categoryService;
//    }

    public BookService() {}

    public List<Book> findAll() {
        List<Book> result = new ArrayList<Book>();
        this.bookRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Book> findAllById(Long categoryId) {
        List<Book> result = new ArrayList<Book>();
        this.bookRepository.findAllById(Collections.singleton(categoryId)).forEach(result::add);
        return result;
    }

    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book saveBook(String name, Integer numberOfCopies, MultipartFile image, Long categoryId) throws IOException {
        Category category = this.categoryService.findById(categoryId);
        Book book = new Book(null, name, numberOfCopies, category);
        return this.bookRepository.save(book);
    }

    public Book saveBook(Book book) {
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

    public boolean exists(Long id) {
        return this.bookRepository.findById(id) != null;
    }
}
