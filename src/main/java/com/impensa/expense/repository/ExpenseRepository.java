package com.impensa.expense.repository;

import com.impensa.expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomas Kozakas
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
