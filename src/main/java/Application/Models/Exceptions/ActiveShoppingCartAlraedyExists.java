package Application.Models.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ActiveShoppingCartAlraedyExists extends RuntimeException{
    public ActiveShoppingCartAlraedyExists() {
        super("Active Shopping Cart Alraedy Exists.");
    }
}

