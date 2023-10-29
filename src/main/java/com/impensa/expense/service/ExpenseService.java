package com.impensa.expense.service;

import com.impensa.expense.model.Expense;
import com.impensa.expense.model.dto.ExpenseDTO;
import com.impensa.expense.model.mapper.ExpenseMapper;
import com.impensa.expense.repository.ExpenseRepository;
import com.impensa.expense.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Tomas Kozakas
 */

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final UserService userService;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    public List<ExpenseDTO> getAllExpenses(String jwtToken) {
        UUID id = userService.getUserFromToken(jwtToken).getId();
        return expenseRepository
                .findAll()
                .stream()
                .filter(expense -> expense.getUserId().equals(id)).toList()
                .stream()
                .map(expenseMapper::toExpenseDTO).toList();
    }

    public ExpenseDTO addExpense(Expense expense, String jwtToken) {
        expense.setUserId(userService.getUserIdFromToken(jwtToken));
        expenseRepository.save(expense);
        return expenseMapper.toExpenseDTO(expense);
    }

    @Transactional
    public Response updateExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.findById(expenseDTO.getExpenseId()).orElseThrow();

        expenseMapper.updateExpenseFromDto(expenseDTO, expense);
        expenseRepository.save(expense);

        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message("Expense with ID " + expenseDTO.getExpenseId() + " was updated")
                .build();
    }

    @Transactional
    public Response deleteExpense(UUID id) {
        expenseRepository.deleteById(id);
        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message("Expense with ID " + id + " was deleted")
                .build();
    }
}
