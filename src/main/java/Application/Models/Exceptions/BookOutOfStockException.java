package Application.Models.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class BookOutOfStockException extends RuntimeException{
    public BookOutOfStockException(Long id) {
        super(String.format("Book with id %d is out of stock!", id));
    }
}
