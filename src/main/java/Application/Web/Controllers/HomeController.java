package Application.Web.Controllers;

import Application.Models.Book;
import Application.Repositories.BookRepository;
import Application.Services.BookService;
import Application.Services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    private IBookService bookService;

    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String indexPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(HttpServletResponse res, HttpServletRequest req, Model model) {

        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "home";
    }
}
