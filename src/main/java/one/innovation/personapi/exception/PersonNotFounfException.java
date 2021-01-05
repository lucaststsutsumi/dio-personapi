package one.innovation.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFounfException extends Exception {
    public PersonNotFounfException(Long id) {
        super("Person not found with ID " + id);
    }
}
