package Application.Services;

import Application.Models.*;
import Application.Models.Exceptions.ActiveShoppingCartAlraedyExists;
import Application.Models.Exceptions.BookOutOfStockException;
import Application.Models.Exceptions.ShoppingCartIsNotActiveException;
import Application.Repositories.IBookRepository;
import Application.Repositories.ICartItemRepository;
import Application.Repositories.IShoppingCartRepository;
import Application.Repositories.ITransactionRepository;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private ICartItemRepository cartItemRepository;
    @Autowired
    private IBookService bookService;
    @Autowired
    private IStripeService stripeService;
    private final IUserService userService;

    public ShoppingCartService(UserService userService) {
        this.userService = userService;
    }

    public ShoppingCart createShoppingCart(String userId) {
        User user = this.userService.findById(userId);
        if (shoppingCartExists(userId)) {
            throw new ActiveShoppingCartAlraedyExists();
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setStatus(Status.CREATED);
        shoppingCart.setDateCreated(LocalDateTime.now());
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public boolean shoppingCartExists(String userId) {
        User user = this.userService.findById(userId);
        return this.shoppingCartRepository.existsByUserUsernameAndStatus(user.getUsername(), Status.CREATED);
    }

    public ShoppingCart addBookToShoppingCart(String userId, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCartOrCreateNew(userId);
        Book book = this.bookService.findById(bookId);
        List<Book> books = shoppingCart.getBooks();
        books.add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public void saveShoppingCart(ShoppingCart shoppingCart, int price) {
        shoppingCart.setStatus(Status.SUCCESSFUL);
        shoppingCart.setPrice(price);
        this.shoppingCartRepository.save(shoppingCart);
    }

    public void saveTransaction(ChargeRequest chargeRequest) {
        this.transactionRepository.save(chargeRequest);
    }

    public ShoppingCart removeBookFromShoppingCart(String userId, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCartOrCreateNew(userId);
        shoppingCart.setBooks(
                shoppingCart.getBooks()
                        .stream()
                        .filter(item -> !item.getId().equals(bookId))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getActiveShoppingCartOrCreateNew(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, Status.CREATED);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(this.userService.findById(userId));
            shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        }
        return shoppingCart;
    }

    public ShoppingCart cancelActiveShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, Status.CREATED);
        if (shoppingCart == null) {
            throw new ShoppingCartIsNotActiveException();
        }
        shoppingCart.setStatus(Status.CANCELLED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public ShoppingCart checkoutShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, Status.CREATED);
        if (shoppingCart == null) {
            throw new ShoppingCartIsNotActiveException();
        }

        int price = 0;
        List<Book> books = shoppingCart.getBooks();

        for (Book book : books) {
            if (book.getNumberOfCopies() <= 0) {
                throw new BookOutOfStockException(book.getId());
            }
            book.setNumberOfCopies(book.getNumberOfCopies() -1);
        }
        shoppingCart.setBooks(books);
        shoppingCart.setStatus(Status.SUCCESSFUL);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart validShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, Status.CREATED);
        if (shoppingCart == null) {
            throw new ShoppingCartIsNotActiveException();
        }

        int price = 0;
        List<Book> books = shoppingCart.getBooks();

        for (Book book : books) {
            if (book.getNumberOfCopies() <= 0) {
                throw new BookOutOfStockException(book.getId());
            }
            book.setNumberOfCopies(book.getNumberOfCopies() -1);
            price += 1000; //sekoja kniga e 1000 centi
        }

        shoppingCart.setBooks(books);
        shoppingCart.setPrice(price);
        this.shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }
}
