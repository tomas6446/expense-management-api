package com.impensa.expense.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Tomas Kozakas
 */

@Entity
@Table(name = "expenses")

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private Double amount;
    private String description;
    private String category;
    private LocalDateTime date;

    public Expense() {
        date = LocalDateTime.now();
    }
}
