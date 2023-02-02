package com.impensa.expense.response;

import lombok.*;

/**
 * @author Tomas Kozakas
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse extends Response {
    private String message;
    private String jwtToken;
}
