package com.impensa.expense.service;

import com.impensa.expense.dto.LoginDTO;
import com.impensa.expense.dto.RegisterDTO;
import com.impensa.expense.model.Role;
import com.impensa.expense.model.User;
import com.impensa.expense.repository.UserRepository;
import com.impensa.expense.response.AuthenticationResponse;
import com.impensa.expense.response.Response;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public Response register(RegisterDTO registerDTO) {
        if (userService.userExists(registerDTO.getEmail())) {
            return new Response("User already exists!");
        }

        User user = User.builder()
                .name(registerDTO.getName())
                .currency(registerDTO.getCurrency())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.USER).build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .message("Success").build();
    }

    public Response login(LoginDTO loginDTO) {
        if (!userService.userExists(loginDTO.getEmail())) {
            return new Response("Invalid Credentials");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .message("Success").build();
    }

    public boolean verify(String jwtToken) {
        User user = User.builder()
                .email(jwtService.extractUsername(jwtToken)).build();
        if (jwtService.isTokenValid(jwtToken, user)) {
            return true;
        }
        throw new JwtException("Token expired");
    }
}

