package com.impensa.expense.service;

import com.impensa.expense.model.Role;
import com.impensa.expense.model.User;
import com.impensa.expense.model.dto.LoginDTO;
import com.impensa.expense.model.dto.RegisterDTO;
import com.impensa.expense.model.mapper.UserMapper;
import com.impensa.expense.repository.UserRepository;
import com.impensa.expense.response.AuthenticationResponse;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationResponse register(RegisterDTO registerDTO) throws Exception {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new Exception("User already exists!");
        }

        User user = userMapper.registerDtoToUser(registerDTO);
        user.setRole(Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .timestamp(LocalDateTime.now())
                .jwtToken(jwtToken)
                .message("Success")
                .expiresAt(jwtService.extractExpiration(jwtToken))
                .build();
    }

    public AuthenticationResponse login(LoginDTO loginDTO) throws Exception {
        if (userRepository.findByEmail(loginDTO.getEmail()).isEmpty()) {
            throw new Exception("Bad Credentials");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .timestamp(LocalDateTime.now())
                .jwtToken(jwtToken)
                .message("Success")
                .expiresAt(jwtService.extractExpiration(jwtToken))
                .build();
    }

    public Boolean verify(String jwtToken) {
        User user = User.builder().email(jwtService.extractUsername(jwtToken)).build();
        if (user == null || !jwtService.isTokenValid(jwtToken, user)) {
            throw new JwtException("Jwt not valid");
        } else {
            return true;
        }
    }
}

