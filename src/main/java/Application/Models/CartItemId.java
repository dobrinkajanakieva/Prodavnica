package Application.Models;
import java.io.Serializable;

public class CartItemId implements Serializable {

    private Long shoppingCart;
    private Long book;

    public CartItemId() {
    }

    public CartItemId(Long shoppingCartId, Long bookId) {
        this.shoppingCart = shoppingCartId;
        this.book = bookId;
    }
}
