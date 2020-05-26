package Application.Models.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ThereAreBooksWithThisCategoryException extends RuntimeException {
    public ThereAreBooksWithThisCategoryException(Long id) {
        super("NASHA PORAKA");
    }
}
