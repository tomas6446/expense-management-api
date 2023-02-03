package com.impensa.expense.service;

import com.impensa.expense.dto.DashboardDTO;
import com.impensa.expense.model.User;
import com.impensa.expense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        List<User> users = userRepository.findAll();
        User foundUser = users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return foundUser != null;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public DashboardDTO getUserFromToken(String token) {
        String email = jwtService.extractUsername(token);
        User foundUser = userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
        return DashboardDTO.builder()
                .name(foundUser.getName())
                .currency(foundUser.getCurrency())
                .email(foundUser.getEmail())
                .build();
    }
}