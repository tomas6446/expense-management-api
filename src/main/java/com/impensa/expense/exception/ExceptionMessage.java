package com.impensa.expense.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Tomas Kozakas
 */

@Getter
@Setter
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String path;

    public ExceptionMessage() {
        timestamp = LocalDateTime.now();
    }

    public ExceptionMessage(String message, String path) {
        this();
        this.message = message;
        this.path = path;
    }
}