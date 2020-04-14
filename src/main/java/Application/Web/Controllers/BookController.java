package Application.Web.Controllers;

import Application.Models.Category;
import Application.Models.Book;
import Application.Services.CategoryService;
import Application.Services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private CategoryService categoryService;

    public BookController(BookService bookService,
                             CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getBooksPage(Model model) {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/new")
    public String addNewBookPage(Model model) {
        List<Category> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(Model model, @PathVariable Long id) {
        List<Category> categories = this.categoryService.findAll();
        try {
            Book book = this.bookService.findById(id);
            model.addAttribute("categories", categories);
            model.addAttribute("book", book);
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getMessage();
        }
        return "new";
    }

    @PostMapping
    public String saveBook(
            @Valid @ModelAttribute("book")  Book book,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("categories", categories);
            return "new";
        }
        book.setCategory(this.categoryService.findById(book.getCategory().getId()));
        this.bookService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }
}
