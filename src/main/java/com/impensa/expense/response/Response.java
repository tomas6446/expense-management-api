package com.impensa.expense.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Tomas Kozakas
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Response {
    protected String message;
}
