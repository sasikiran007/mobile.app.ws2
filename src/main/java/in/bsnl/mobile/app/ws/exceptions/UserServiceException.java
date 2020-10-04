package in.bsnl.mobile.app.ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserServiceException extends RuntimeException {
    private static final long serialVersionUID = 1348771109171435607L;
    public UserServiceException(String message) {
        super(message);}
}
