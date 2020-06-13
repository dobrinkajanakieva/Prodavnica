package Application.Web.Controllers;

import Application.Models.Category;
import Application.Models.Book;
import Application.Models.ChargeRequest;
import Application.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private IBookService bookService;
    private ICategoryService categoryService;
    private final IShoppingCartService shoppingCartService;
    private final IAuthService authService;

    public BookController(BookService bookService,
                          CategoryService categoryService,
                          ShoppingCartService shoppingCartService,
                          AuthService authService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
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
//        if (book.getImage() != null && !book.getImage().getOriginalFilename().isEmpty()) {
//            byte[] bytes = book.getImage().getBytes();
//            book.setImageBytes(book.getImage());
//            String base64Image = String.format("data:%s;base64,%s", book.getImage().getContentType(), Base64.getEncoder().encodeToString(bytes));
//            book.setImageBase64(base64Image);
//        }
//        else if (book.getId() != null && this.bookService.exists(book.getId())) {
//            book.setImageBase64(String.format("data:image/jpeg;base64,%s", Base64.getEncoder().encodeToString(this.bookService.findById(book.getId()).getImageBytes())));
//        }
        book.setCategory(this.categoryService.findById(book.getCategory().getId()));
        this.bookService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/addbook")
    public String addBook(@PathVariable Long id) {

        if (!this.shoppingCartService.shoppingCartExists(this.authService.getCurrentUserId()))
            this.shoppingCartService.createShoppingCart(this.authService.getCurrentUserId());
        this.shoppingCartService.addBookToShoppingCart(this.authService.getCurrentUserId(), id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }
}
