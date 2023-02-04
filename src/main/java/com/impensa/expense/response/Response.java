package com.impensa.expense.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Tomas Kozakas
 */

@Getter
@SuperBuilder
public class Response {
    protected LocalDateTime timestamp;
    protected String message;
}
