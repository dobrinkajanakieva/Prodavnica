package Application.Services;

import Application.Models.CartItem;
import Application.Models.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {
    ShoppingCart createShoppingCart(String userId);
    ShoppingCart addBookToShoppingCart(String userId, Long bookId);
    ShoppingCart removeBookFromShoppingCart(String userId, Long bookId);
    ShoppingCart getActiveShoppingCartOrCreateNew(String userId);
    ShoppingCart cancelActiveShoppingCart(String userId);
    ShoppingCart checkoutShoppingCart(String userId);
}
