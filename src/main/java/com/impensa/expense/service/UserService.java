package com.impensa.expense.service;

import com.impensa.expense.model.User;
import com.impensa.expense.model.dto.DashboardDTO;
import com.impensa.expense.model.dto.UserDTO;
import com.impensa.expense.model.dto.UserUpdateDTO;
import com.impensa.expense.model.mapper.UserMapper;
import com.impensa.expense.repository.UserRepository;
import com.impensa.expense.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public User getUserFromToken(String jwtToken) {
        String email = jwtService.extractUsername(jwtToken);
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    public UUID getUserIdFromToken(String jwtToken) {
        return getUserFromToken(jwtToken).getId();
    }

    public UserDTO getUserData(String jwtToken) {
        User user = getUserFromToken(jwtToken);
        return userMapper.toUserDTO(user);
    }

    public DashboardDTO getUser(String jwtToken) {
        User user = getUserFromToken(jwtToken);
        return userMapper.toDashboardDTO(user);
    }

    @Transactional
    public Response updateUserData(UserUpdateDTO userDTO, String token) {
        UUID id = getUserIdFromToken(token);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid current password provided.");
        }

        if (userDTO.getNewPassword().equals(userDTO.getPassword())) {
            throw new IllegalStateException("New password cannot be the same as the current password.");
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
        userRepository.save(user);

        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message("User with ID " + id + " was updated")
                .build();
    }
}
