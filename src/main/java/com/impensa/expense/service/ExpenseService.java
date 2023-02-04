package com.impensa.expense.service;

import com.impensa.expense.dto.ExpenseDTO;
import com.impensa.expense.model.Expense;
import com.impensa.expense.repository.ExpenseRepository;
import com.impensa.expense.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Kozakas
 */

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final UserService userService;
    private final ExpenseRepository expenseRepository;

    public List<ExpenseDTO> getAllExpenses(String jwtToken) {
        Long id = userService.getUserFromToken(jwtToken).getId();
        List<Expense> expenses = expenseRepository
                .findAll()
                .stream()
                .filter(expense -> expense.getUserId().equals(id))
                .toList();
        List<ExpenseDTO> expenseDTOS = new ArrayList<>();
        expenses.forEach(expense ->
                expenseDTOS.add(new ExpenseDTO(id,
                        expense.getId(),
                        expense.getAmount(),
                        expense.getDescription(),
                        expense.getCategory(),
                        expense.getDate())));
        return expenseDTOS;
    }

    public ExpenseDTO addExpense(Expense expense, String jwtToken) {
        expense.setUserId(userService.getUserIdFromToken(jwtToken));
        expenseRepository.save(expense);
        return ExpenseDTO.builder()
                .user_id(expense.getUserId())
                .expense_id(expense.getId())
                .expense_amount(expense.getAmount())
                .expense_category(expense.getCategory())
                .expense_date(expense.getDate())
                .expense_description(expense.getDescription())
                .build();
    }

    public Response updateExpense(ExpenseDTO expenseDTO) throws Exception {
        Long id = expenseDTO.getExpense_id();
        expenseRepository.findById(id)
                .map(s -> {
                    s.setDate(expenseDTO.getExpense_date());
                    s.setAmount(expenseDTO.getExpense_amount());
                    s.setDescription(expenseDTO.getExpense_description());
                    s.setId(expenseDTO.getExpense_id());
                    s.setUserId(expenseDTO.getUser_id());
                    return expenseRepository.save(s);
                })
                .orElseThrow(() -> new Exception("Expense with id " + id + " not found"));
        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message("Expense with ID " + id + " was updated")
                .build();
    }

    public Response deleteExpense(Long id) {
        expenseRepository.deleteById(id);
        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message("Expense with ID " + id + " was deleted")
                .build();
    }
}
