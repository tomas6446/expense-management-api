package com.impensa.expense.controller;

import com.impensa.expense.dto.LoginDTO;
import com.impensa.expense.dto.RegisterDTO;
import com.impensa.expense.exception.AuthorizationException;
import com.impensa.expense.response.Response;
import com.impensa.expense.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> register(@RequestBody RegisterDTO registerDTO) throws AuthorizationException {
        return ResponseEntity.ok((authenticationService.register(registerDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO) throws AuthorizationException {
        return ResponseEntity.ok(authenticationService.login(loginDTO));
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> login(@RequestHeader("jwtToken") String jwtToken) {
        return ResponseEntity.ok(authenticationService.verify(jwtToken));
    }
}