package com.impensa.expense.controller;

import com.impensa.expense.model.dto.DashboardDTO;
import com.impensa.expense.model.dto.UserDTO;
import com.impensa.expense.model.dto.UserUpdateDTO;
import com.impensa.expense.response.Response;
import com.impensa.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<DashboardDTO> dashboard(@RequestHeader("jwtToken") String jwtToken) {
        return List.of(userService.getUser(jwtToken));
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUserData(@RequestHeader("jwtToken") String jwtToken) {
        return List.of(userService.getUserData(jwtToken));
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Response updateUserData(@RequestBody UserUpdateDTO userDTO, @RequestHeader("jwtToken") String jwtToken) {
        return userService.updateUserData(userDTO, jwtToken);
    }
}
