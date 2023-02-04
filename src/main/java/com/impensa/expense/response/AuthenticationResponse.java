package com.impensa.expense.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Tomas Kozakas
 */

@Getter
@SuperBuilder
public class AuthenticationResponse extends Response {
    private Date expiresAt;
    private String jwtToken;
}
