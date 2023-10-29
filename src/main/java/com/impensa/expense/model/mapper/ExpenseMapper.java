package com.impensa.expense.model.mapper;

import com.impensa.expense.model.Expense;
import com.impensa.expense.model.dto.ExpenseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author Tomas Kozakas
 */
@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseDTO toExpenseDTO(Expense expense);

    Expense toExpense(ExpenseDTO expenseDTO);

    void updateExpenseFromDto(ExpenseDTO expenseDTO, @MappingTarget Expense expense);
}
