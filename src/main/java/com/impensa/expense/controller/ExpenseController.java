package com.impensa.expense.controller;

import com.impensa.expense.dto.DashboardDTO;
import com.impensa.expense.dto.ExpenseDTO;
import com.impensa.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomas Kozakas
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class ExpenseController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<DashboardDTO> dashboard(@RequestHeader("jwtToken") String jwtToken) {
        return ResponseEntity.ok((userService.getUserFromToken(jwtToken)));
    }

    @GetMapping("/expenses")
    public ResponseEntity<ExpenseDTO> expenses(@RequestHeader("jwtToken") String jwtToken) {
        return ResponseEntity.ok(new ExpenseDTO());
    }
}
