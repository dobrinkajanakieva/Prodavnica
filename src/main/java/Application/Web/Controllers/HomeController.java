package Application.Web.Controllers;

import Application.Models.Book;
//import Application.Repositories.BookRepository;
import Application.Models.ShoppingCart;
import Application.Models.User;
import Application.Repositories.IShoppingCartRepository;
import Application.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    public HomeController() { }

    @GetMapping
    public String indexPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(HttpServletResponse res, HttpServletRequest req, Model model) {
        return "home";
    }
}
