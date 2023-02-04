package com.impensa.expense.controller;

import com.impensa.expense.dto.ExpenseDTO;
import com.impensa.expense.model.Expense;
import com.impensa.expense.response.Response;
import com.impensa.expense.service.ExpenseService;
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
public class ExpenseController {
    private final ExpenseService expenseService;


    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestHeader("jwtToken") String jwtToken) {
        return ResponseEntity.ok(expenseService.getAllExpenses(jwtToken));
    }

    @PostMapping("/expense")
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody Expense expense, @RequestHeader("jwtToken") String jwtToken) {
        return ResponseEntity.ok(expenseService.addExpense(expense, jwtToken));
    }

    @PutMapping("/expense")
    public ResponseEntity<Response> updateExpense(@RequestBody ExpenseDTO expenseDTO) throws Exception {
        return ResponseEntity.ok(expenseService.updateExpense(expenseDTO));
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<Response> deleteExpense(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(expenseService.deleteExpense(id));
    }
}
