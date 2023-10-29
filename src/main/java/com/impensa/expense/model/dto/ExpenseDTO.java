package com.impensa.expense.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Tomas Kozakas
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("expense_id")
    private UUID expenseId;

    @JsonProperty("expense_amount")
    private Double expenseAmount;

    @JsonProperty("expense_description")
    private String expenseDescription;

    @JsonProperty("expense_category")
    private String expenseCategory;

    @JsonProperty("expense_date")
    private LocalDateTime expenseDate;
}
