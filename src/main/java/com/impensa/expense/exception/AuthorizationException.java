package com.impensa.expense.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tomas Kozakas
 */
@Getter
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AuthorizationException extends Exception {
    private final HttpStatus status = HttpStatus.FORBIDDEN;
    public AuthorizationException(String error) {
        super(error);
    }
}
