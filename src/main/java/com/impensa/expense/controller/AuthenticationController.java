package com.impensa.expense.controller;

import com.impensa.expense.model.dto.LoginDTO;
import com.impensa.expense.model.dto.RegisterDTO;
import com.impensa.expense.response.AuthenticationResponse;
import com.impensa.expense.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tomas Kozakas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody RegisterDTO registerDTO) throws Exception {
        return authenticationService.register(registerDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody LoginDTO loginDTO) throws Exception {
        return authenticationService.login(loginDTO);
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public Boolean login(@RequestHeader("jwtToken") String jwtToken) {
        return authenticationService.verify(jwtToken);
    }
}
