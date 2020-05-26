package Application.Web.RestControllers;

import Application.Models.*;
import Application.Services.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;
    private final IAuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public ShoppingCart createShoppingCart() {
        return this.shoppingCartService.createShoppingCart( this.authService.getCurrentUserId());
    }

    @GetMapping("/{bookId}/books")
    public ShoppingCart addBookToShoppingCart(@PathVariable Long bookId) {
        return this.shoppingCartService.addBookToShoppingCart(
                this.authService.getCurrentUserId(),
                bookId
        );
    }

    @DeleteMapping("/{bookId}/books")
    public ShoppingCart removeProductFromShoppingCart(@PathVariable Long bookId) {
        return this.shoppingCartService.removeBookFromShoppingCart(
                this.authService.getCurrentUserId(),
                bookId
        );
    }

    @DeleteMapping
    public ShoppingCart cancelActiveShoppingCart() {
        return this.shoppingCartService.cancelActiveShoppingCart(this.authService.getCurrentUserId());
    }

    @GetMapping("/checkout")
    public ShoppingCart checkoutShoppingCart() {
        return this.shoppingCartService.checkoutShoppingCart(authService.getCurrentUserId());
    }

}
