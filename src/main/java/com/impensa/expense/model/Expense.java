package com.impensa.expense.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
@RequiredArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double amount;
    private String description;
    private String category;
    private LocalDateTime date;
    private Long userId;
}
