package com.martin.api.util.mapper;

import com.martin.api.persistence.model.Expense;
import com.martin.api.util.dto.expense.ExpenseRequest;
import com.martin.api.util.dto.expense.ExpenseResponse;
import java.util.List;

public class ExpenseMapper {

  public static ExpenseResponse toExpenseResponse(Expense expense) {
    if (expense == null) {
      return null;
    }

    return new ExpenseResponse(
        expense.getId(),
        expense.getPrice(),
        expense.getCategory(),
        expense.getDate()
    );
  }

  public static List<ExpenseResponse> toListExpenseResponse(List<Expense> expenses) {
    if (expenses == null) {
      return null;
    }

    return expenses.stream()
        .map(ExpenseMapper::toExpenseResponse)
        .toList();
  }

  public static Expense toExpenseEntity(ExpenseRequest request) {
    if (request == null) {
      return null;
    }

    Expense expense = new Expense();
    expense.setPrice(request.price());
    expense.setCategory(request.category());

    return expense;
  }

  public static void updateExpenseEntity(Expense oldExpense, ExpenseRequest request) {
    if (request != null) {
      if (request.price() != null) {
        oldExpense.setPrice(request.price());
      }
      if (request.category() != null) {
        oldExpense.setCategory(request.category());
      }
    }
  }
}
