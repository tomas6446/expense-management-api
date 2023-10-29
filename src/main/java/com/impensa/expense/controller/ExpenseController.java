package com.impensa.expense.controller;

import com.impensa.expense.model.Expense;
import com.impensa.expense.model.dto.ExpenseDTO;
import com.impensa.expense.response.Response;
import com.impensa.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Tomas Kozakas
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class ExpenseController {
    private final ExpenseService expenseService;


    @GetMapping("/expenses")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseDTO> getAllExpenses(@RequestHeader("jwtToken") String jwtToken) {
        return expenseService.getAllExpenses(jwtToken);
    }

    @PostMapping("/expense")
    @ResponseStatus(HttpStatus.OK)
    public ExpenseDTO addExpense(@RequestBody Expense expense, @RequestHeader("jwtToken") String jwtToken) {
        return expenseService.addExpense(expense, jwtToken);
    }

    @PutMapping("/expense")
    @ResponseStatus(HttpStatus.OK)
    public Response updateExpense(@RequestBody ExpenseDTO expenseDTO) {
        return expenseService.updateExpense(expenseDTO);
    }

    @DeleteMapping("/expense/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteExpense(@PathVariable UUID id) {
        return expenseService.deleteExpense(id);
    }
}
