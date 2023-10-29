package com.impensa.expense.model.mapper;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Tomas Kozakas
 */

@Component
public class PasswordEncodingMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncodingMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Named("encodePassword")
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
