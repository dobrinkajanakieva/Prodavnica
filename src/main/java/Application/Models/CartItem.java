package Application.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cartItem")
@IdClass(CartItemId.class)
public class CartItem implements Serializable {

    @Id
    @ManyToOne(targetEntity = ShoppingCart.class)
    private ShoppingCart shoppingCart;

    @Id
    @ManyToOne(targetEntity = Book.class)
    private Book book;

    @NotNull
    private Integer amount;

    public CartItem() {}

    public CartItem(ShoppingCart shoppingCart, Book book, Integer amount) {
        this.shoppingCart = shoppingCart;
        this.book = book;
        this.amount = amount;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) { this.shoppingCart = shoppingCart; }
    public void setBook(Book book) { this.book = book; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public ShoppingCart getShoppingCart() { return this.shoppingCart; }
    public Book getBookId() { return this.book; }
    public Integer getAmount() {return this.amount; }
}

