package com.impensa.expense.service;

import com.impensa.expense.dto.LoginDTO;
import com.impensa.expense.dto.RegisterDTO;
import com.impensa.expense.exception.AuthorizationException;
import com.impensa.expense.model.Role;
import com.impensa.expense.model.User;
import com.impensa.expense.response.AuthenticationResponse;
import com.impensa.expense.response.Response;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Response register(RegisterDTO registerDTO) throws AuthorizationException {
        if (userService.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new AuthorizationException("User already exists!");
        }
        User user = User.builder()
                .name(registerDTO.getName())
                .currency(registerDTO.getCurrency())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.USER).build();

        userService.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .timestamp(LocalDateTime.now())
                .jwtToken(jwtToken)
                .message("Success")
                .expiresAt(jwtService.extractExpiration(jwtToken))
                .build();
    }

    public Response login(LoginDTO loginDTO) throws AuthorizationException {
        if (userService.findByEmail(loginDTO.getEmail()).isEmpty()) {
            throw new AuthorizationException("Bad Credentials");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        User user = userService.findByEmail(loginDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .timestamp(LocalDateTime.now())
                .jwtToken(jwtToken)
                .message("Success")
                .expiresAt(jwtService.extractExpiration(jwtToken))
                .build();
    }

    public boolean verify(String jwtToken) {
        User user = User.builder().email(jwtService.extractUsername(jwtToken)).build();
        if (user != null && jwtService.isTokenValid(jwtToken, user)) {
            return true;
        }
        throw new JwtException("Jwt not valid");
    }
}

