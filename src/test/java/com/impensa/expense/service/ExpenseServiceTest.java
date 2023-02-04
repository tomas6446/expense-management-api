package com.impensa.expense.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.impensa.expense.dto.ExpenseDTO;
import com.impensa.expense.model.Expense;
import com.impensa.expense.model.Role;
import com.impensa.expense.model.User;
import com.impensa.expense.repository.ExpenseRepository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExpenseService.class})
@ExtendWith(SpringExtension.class)
class ExpenseServiceTest {
    @MockBean
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseService expenseService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link ExpenseService#getAllExpenses(String)}
     */
    @Test
    void testGetAllExpenses() {
        when(userService.getUserFromToken((String) any())).thenReturn(new User());
        when(expenseRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(expenseService.getAllExpenses("ABC123").isEmpty());
        verify(userService).getUserFromToken((String) any());
        verify(expenseRepository).findAll();
    }

    /**
     * Method under test: {@link ExpenseService#getAllExpenses(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllExpenses2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.User.getId()" because the return value of "com.impensa.expense.service.UserService.getUserFromToken(String)" is null
        //       at com.impensa.expense.service.ExpenseService.getAllExpenses(ExpenseService.java:25)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userService.getUserFromToken((String) any())).thenReturn(null);
        when(expenseRepository.findAll()).thenReturn(new ArrayList<>());
        expenseService.getAllExpenses("ABC123");
    }

    /**
     * Method under test: {@link ExpenseService#getAllExpenses(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllExpenses3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.equals(Object)" because the return value of "com.impensa.expense.model.Expense.getUserId()" is null
        //       at com.impensa.expense.service.ExpenseService.lambda$getAllExpenses$0(ExpenseService.java:29)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
        //       at java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
        //       at java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
        //       at com.impensa.expense.service.ExpenseService.getAllExpenses(ExpenseService.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userService.getUserFromToken((String) any())).thenReturn(new User());

        ArrayList<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense());
        when(expenseRepository.findAll()).thenReturn(expenseList);
        expenseService.getAllExpenses("ABC123");
    }

    /**
     * Method under test: {@link ExpenseService#getAllExpenses(String)}
     */
    @Test
    void testGetAllExpenses4() {
        when(userService.getUserFromToken((String) any())).thenReturn(new User());

        ArrayList<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense(123L, 10.0d, "The characteristics of someone or something", "Category",
                LocalDateTime.of(1, 1, 1, 1, 1), 123L));
        when(expenseRepository.findAll()).thenReturn(expenseList);
        assertTrue(expenseService.getAllExpenses("ABC123").isEmpty());
        verify(userService).getUserFromToken((String) any());
        verify(expenseRepository).findAll();
    }

    /**
     * Method under test: {@link ExpenseService#getAllExpenses(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllExpenses5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.Expense.getUserId()" because "expense" is null
        //       at com.impensa.expense.service.ExpenseService.lambda$getAllExpenses$0(ExpenseService.java:29)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
        //       at java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
        //       at java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
        //       at com.impensa.expense.service.ExpenseService.getAllExpenses(ExpenseService.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userService.getUserFromToken((String) any())).thenReturn(new User());

        ArrayList<Expense> expenseList = new ArrayList<>();
        expenseList.add(null);
        when(expenseRepository.findAll()).thenReturn(expenseList);
        expenseService.getAllExpenses("ABC123");
    }

    /**
     * Method under test: {@link ExpenseService#getAllExpenses(String)}
     */
    @Test
    void testGetAllExpenses6() {
        when(userService.getUserFromToken((String) any()))
                .thenReturn(new User(123L, "Name", "jane.doe@example.org", "GBP", "iloveyou", Role.USER));

        ArrayList<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense(123L, 10.0d, "The characteristics of someone or something", "Category",
                LocalDateTime.of(1, 1, 1, 1, 1), 123L));
        when(expenseRepository.findAll()).thenReturn(expenseList);
        assertEquals(1, expenseService.getAllExpenses("ABC123").size());
        verify(userService).getUserFromToken((String) any());
        verify(expenseRepository).findAll();
    }

    /**
     * Method under test: {@link ExpenseService#addExpense(Expense, String)}
     */
    @Test
    void testAddExpense() {
        when(userService.getUserIdFromToken((String) any())).thenReturn(1L);
        when(expenseRepository.save((Expense) any())).thenReturn(new Expense());
        Expense expense = new Expense();
        ExpenseDTO actualAddExpenseResult = expenseService.addExpense(expense, "ABC123");
        assertNull(actualAddExpenseResult.getExpense_amount());
        assertEquals(1L, actualAddExpenseResult.getUser_id().longValue());
        assertNull(actualAddExpenseResult.getExpense_id());
        assertNull(actualAddExpenseResult.getExpense_description());
        assertNull(actualAddExpenseResult.getExpense_category());
        verify(userService).getUserIdFromToken((String) any());
        verify(expenseRepository).save((Expense) any());
        assertEquals(1L, expense.getUserId().longValue());
    }

    /**
     * Method under test: {@link ExpenseService#addExpense(Expense, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddExpense2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.model.Expense.setUserId(java.lang.Long)" because "expense" is null
        //       at com.impensa.expense.service.ExpenseService.addExpense(ExpenseService.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userService.getUserIdFromToken((String) any())).thenReturn(1L);
        when(expenseRepository.save((Expense) any())).thenReturn(new Expense());
        expenseService.addExpense(null, "ABC123");
    }

    /**
     * Method under test: {@link ExpenseService#updateExpense(ExpenseDTO)}
     */
    @Test
    void testUpdateExpense() throws Exception {
        when(expenseRepository.save((Expense) any())).thenReturn(new Expense());
        when(expenseRepository.findById((Long) any())).thenReturn(Optional.of(new Expense()));
        assertEquals("Expense with ID null was updated", expenseService.updateExpense(new ExpenseDTO()).getMessage());
        verify(expenseRepository).save((Expense) any());
        verify(expenseRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExpenseService#updateExpense(ExpenseDTO)}
     */
    @Test
    void testUpdateExpense2() throws Exception {
        when(expenseRepository.save((Expense) any())).thenReturn(null);
        when(expenseRepository.findById((Long) any())).thenReturn(Optional.of(new Expense()));
        assertThrows(Exception.class, () -> expenseService.updateExpense(new ExpenseDTO()));
        verify(expenseRepository).save((Expense) any());
        verify(expenseRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExpenseService#updateExpense(ExpenseDTO)}
     */
    @Test
    void testUpdateExpense3() throws Exception {
        when(expenseRepository.save((Expense) any())).thenReturn(new Expense());
        when(expenseRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> expenseService.updateExpense(new ExpenseDTO()));
        verify(expenseRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExpenseService#updateExpense(ExpenseDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateExpense4() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.impensa.expense.dto.ExpenseDTO.getExpense_id()" because "expenseDTO" is null
        //       at com.impensa.expense.service.ExpenseService.updateExpense(ExpenseService.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        when(expenseRepository.save((Expense) any())).thenReturn(new Expense());
        when(expenseRepository.findById((Long) any())).thenReturn(Optional.of(new Expense()));
        expenseService.updateExpense(null);
    }

    /**
     * Method under test: {@link ExpenseService#updateExpense(ExpenseDTO)}
     */
    @Test
    void testUpdateExpense5() throws Exception {
        when(expenseRepository.save((Expense) any())).thenReturn(new Expense());
        when(expenseRepository.findById((Long) any())).thenReturn(Optional.of(new Expense()));
        ExpenseDTO expenseDTO = mock(ExpenseDTO.class);
        when(expenseDTO.getExpense_amount()).thenReturn(10.0d);
        when(expenseDTO.getExpense_id()).thenReturn(1L);
        when(expenseDTO.getUser_id()).thenReturn(1L);
        when(expenseDTO.getExpense_description()).thenReturn("Expense description");
        when(expenseDTO.getExpense_date()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals("Expense with ID 1 was updated", expenseService.updateExpense(expenseDTO).getMessage());
        verify(expenseRepository).save((Expense) any());
        verify(expenseRepository).findById((Long) any());
        verify(expenseDTO).getExpense_amount();
        verify(expenseDTO, atLeast(1)).getExpense_id();
        verify(expenseDTO).getUser_id();
        verify(expenseDTO).getExpense_description();
        verify(expenseDTO).getExpense_date();
    }

    /**
     * Method under test: {@link ExpenseService#deleteExpense(Long)}
     */
    @Test
    void testDeleteExpense() {
        doNothing().when(expenseRepository).deleteById((Long) any());
        assertEquals("Expense with ID 123 was deleted", expenseService.deleteExpense(123L).getMessage());
        verify(expenseRepository).deleteById((Long) any());
    }
}

