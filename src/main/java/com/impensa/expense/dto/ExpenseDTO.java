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
    private Long id;
    private Double amount;
    private String description;
    private String category;
    private LocalDateTime date;
}
