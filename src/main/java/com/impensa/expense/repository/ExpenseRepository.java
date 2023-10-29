package com.impensa.expense.repository;

import com.impensa.expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Tomas Kozakas
 */
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

}
