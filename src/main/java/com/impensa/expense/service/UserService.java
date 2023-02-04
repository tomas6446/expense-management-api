package com.impensa.expense.service;

import com.impensa.expense.dto.DashboardDTO;
import com.impensa.expense.dto.UserDTO;
import com.impensa.expense.dto.UserUpdateDTO;
import com.impensa.expense.model.User;
import com.impensa.expense.repository.UserRepository;
import com.impensa.expense.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserFromToken(String jwtToken) {
        String email = jwtService.extractUsername(jwtToken);
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    public Long getUserIdFromToken(String jwtToken) {
        return getUserFromToken(jwtToken).getId();
    }

    public UserDTO getUserData(String jwtToken) {
        User user = getUserFromToken(jwtToken);
        return UserDTO.builder()
                .user_email(user.getEmail())
                .user_name(user.getName())
                .build();
    }

    public Response updateUserData(UserUpdateDTO userDTO, String token) throws Exception {
        Long id = getUserIdFromToken(token);
        userRepository.findById(id).map(s -> {
                    if (!userDTO.getUserNewPassword().equals(userDTO.getUserPassword()) && passwordEncoder.matches(userDTO.getUserPassword(), s.getPassword())) {
                        s.setName(userDTO.getUserName());
                        s.setEmail(userDTO.getUserEmail());
                        s.setPassword(passwordEncoder.encode(userDTO.getUserNewPassword()));
                    } else {
                        throw new BadCredentialsException("Error updating password");
                    }
                    return userRepository.save(s);
                })
                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message("User with ID " + id + " was updated")
                .build();
    }

    public DashboardDTO getUser(String jwtToken) {
        User user = getUserFromToken(jwtToken);
        return DashboardDTO.builder()
                .user_name(user.getName())
                .user_email(user.getEmail())
                .user_currency(user.getCurrency())
                .build();
    }
}