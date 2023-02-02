package com.impensa.expense.service;

import com.impensa.expense.dto.RegisterDTO;
import com.impensa.expense.dto.UserDTO;
import com.impensa.expense.model.User;
import com.impensa.expense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tomas Kozakas
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .currency(userDTO.getCurrency())
                .password(userDTO.getPassword())
                .role(userDTO.getRole()).build();
        userRepository.save(user);
    }

    public void deleteUser(Long studentId) {
        if (userRepository.existsById(studentId)) {
            userRepository.deleteById(studentId);
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getName(), user.getEmail(), user.getCurrency(), user.getPassword(), user.getRole()))
                .collect(Collectors.toList());
    }

    public boolean userExists(String email) {
        List<User> users = userRepository.findAll();
        User foundUser = users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return foundUser != null;
    }

    public void updateUsers(Long id, UserDTO userDTO) throws Exception {
        userRepository.findById(id)
                .map(s -> {
                    s.setName(userDTO.getName());
                    s.setEmail(userDTO.getEmail());
                    s.setCurrency(userDTO.getCurrency());
                    s.setPassword(userDTO.getPassword());
                    s.setRole(userDTO.getRole());

                    return userRepository.save(s);
                })
                .orElseThrow(() -> new Exception("Student with id " + id + " not found"));
    }
}