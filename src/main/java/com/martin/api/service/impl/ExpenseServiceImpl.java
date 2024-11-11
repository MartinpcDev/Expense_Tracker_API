package com.martin.api.service.impl;

import com.martin.api.exception.ExpenseNotFoundException;
import com.martin.api.exception.NotPermitedActionException;
import com.martin.api.exception.UserNotFoundException;
import com.martin.api.persistence.model.Expense;
import com.martin.api.persistence.model.User;
import com.martin.api.persistence.repository.ExpenseRepository;
import com.martin.api.persistence.repository.UserRepository;
import com.martin.api.service.IExpenseService;
import com.martin.api.util.dto.DeleteResponse;
import com.martin.api.util.dto.expense.ExpenseRequest;
import com.martin.api.util.dto.expense.ExpenseResponse;
import com.martin.api.util.mapper.ExpenseMapper;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements IExpenseService {

  private final ExpenseRepository expenseRepository;
  private final UserRepository userRepository;

  @Override
  public List<ExpenseResponse> getAllExpenses(Long userId) {
    return ExpenseMapper.toListExpenseResponse(expenseRepository.findAllByUserId(userId));
  }

  @Override
  public List<ExpenseResponse> getAllExpensesPastWeek(Long userId) {
    LocalDate endDate = LocalDate.now();
    LocalDate startDate = endDate.minusWeeks(1);
    List<Expense> expenses = expenseRepository
        .findByDateBetweenAndUserId(startDate, endDate, userId);
    return ExpenseMapper.toListExpenseResponse(expenses);
  }

  @Override
  public List<ExpenseResponse> getAllExpensesPastMonth(Long userId) {
    LocalDate endDate = LocalDate.now();
    LocalDate startDate = endDate.minusMonths(1);
    List<Expense> expenses = expenseRepository
        .findByDateBetweenAndUserId(startDate, endDate, userId);
    return ExpenseMapper.toListExpenseResponse(expenses);
  }

  @Override
  public List<ExpenseResponse> getAllExpensesLast3Months(Long userId) {
    LocalDate endDate = LocalDate.now();
    LocalDate startDate = endDate.minusMonths(3);
    List<Expense> expenses = expenseRepository
        .findByDateBetweenAndUserId(startDate, endDate, userId);
    return ExpenseMapper.toListExpenseResponse(expenses);
  }

  @Override
  public List<ExpenseResponse> getAllExpensesCustom(LocalDate dateInit, LocalDate dateFinish,
      Long userId) {
    List<Expense> expenses = expenseRepository
        .findAllExpensesWithinDateRange(dateInit, dateFinish, userId);
    return ExpenseMapper.toListExpenseResponse(expenses);
  }

  @Override
  public ExpenseResponse getExpenseById(Long id) {
    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException("Expense con el id " + id + " no existe"));
    return ExpenseMapper.toExpenseResponse(expense);
  }

  @Override
  @Transactional
  public ExpenseResponse saveExpense(ExpenseRequest request, Long userId) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User con el " + userId + " no existe."));
    Expense expense = ExpenseMapper.toExpenseEntity(request);
    expense.setUser(userExists);
    return ExpenseMapper.toExpenseResponse(expenseRepository.save(expense));
  }

  @Override
  @Transactional
  public ExpenseResponse updateExpense(Long id, Long userId, ExpenseRequest request) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User con el " + userId + " no existe."));
    Expense expenseExists = expenseRepository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException("Expense con el id " + id + " no existe"));

    if (!Objects.equals(expenseExists.getUser().getId(), userExists.getId())) {
      throw new NotPermitedActionException("Este expense pertenece a otro usuario");
    }

    ExpenseMapper.updateExpenseEntity(expenseExists, request);

    return ExpenseMapper.toExpenseResponse(expenseRepository.save(expenseExists));
  }

  @Override
  @Transactional
  public DeleteResponse deleteExpense(Long id, Long userId) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User con el " + userId + " no existe."));
    Expense expenseExists = expenseRepository.findById(id)
        .orElseThrow(() -> new ExpenseNotFoundException("Expense con el id " + id + " no existe"));

    if (!Objects.equals(expenseExists.getUser().getId(), userExists.getId())) {
      throw new NotPermitedActionException("Este expense pertenece a otro usuario");
    }

    expenseRepository.delete(expenseExists);

    return new DeleteResponse("Expense Eliminado Satisfactoriamente");
  }
}
