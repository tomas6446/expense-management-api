package com.impensa.expense.controller;

import com.impensa.expense.dto.DashboardDTO;
import com.impensa.expense.dto.UserDTO;
import com.impensa.expense.model.User;
import com.impensa.expense.response.Response;
import com.impensa.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tomas Kozakas
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class UserController {
    private final UserService userService;

    /**
     * Returns List, instead of single Object, because of front end retards
     */
    @GetMapping("/")
    public ResponseEntity<List<DashboardDTO>> dashboard(@RequestHeader("jwtToken") String jwtToken) {
        User user = userService.getUserFromToken(jwtToken);
        DashboardDTO dashboardDTO = DashboardDTO.builder()
                .user_name(user.getName())
                .user_email(user.getEmail())
                .user_currency(user.getCurrency())
                .build();
        return ResponseEntity.ok((List.of(dashboardDTO)));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUserData(@RequestHeader("jwtToken") String jwtToken) {
        return ResponseEntity.ok(List.of(userService.getUserData(jwtToken)));
    }

    @PutMapping("/user")
    public ResponseEntity<Response> updateUserData(@RequestBody UserDTO userDTO, @RequestHeader("jwtToken") String jwtToken) throws Exception {
        return ResponseEntity.ok(userService.updateUserData(userDTO, jwtToken));
    }
}
