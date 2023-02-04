package com.impensa.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Tomas Kozakas
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private Long user_id;
    private Long expense_id;
    private Double expense_amount;
    private String expense_description;
    private String expense_category;
    private LocalDateTime expense_date;
}
