package devric.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PropertyNotFound extends RuntimeException {
    public PropertyNotFound(String msg) {
        super(msg);
    }
}
