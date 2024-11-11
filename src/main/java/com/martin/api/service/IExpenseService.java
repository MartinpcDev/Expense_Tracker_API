package com.martin.api.service;

import com.martin.api.util.dto.DeleteResponse;
import com.martin.api.util.dto.expense.ExpenseRequest;
import com.martin.api.util.dto.expense.ExpenseResponse;
import java.time.LocalDate;
import java.util.List;

public interface IExpenseService {

  List<ExpenseResponse> getAllExpenses(Long userId);

  List<ExpenseResponse> getAllExpensesPastWeek(Long userId);

  List<ExpenseResponse> getAllExpensesPastMonth(Long userId);

  List<ExpenseResponse> getAllExpensesLast3Months(Long userId);

  List<ExpenseResponse> getAllExpensesCustom(LocalDate dateInit, LocalDate dateFinish, Long userId);

  ExpenseResponse getExpenseById(Long id);

  ExpenseResponse saveExpense(ExpenseRequest request, Long userId);

  ExpenseResponse updateExpense(Long id, Long userId, ExpenseRequest request);

  DeleteResponse deleteExpense(Long id, Long userId);

}
