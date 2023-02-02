package com.impensa.expense.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tomas Kozakas
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AuthorizationException extends Exception {
    public AuthorizationException(String error) {
        super(error);
    }
}
