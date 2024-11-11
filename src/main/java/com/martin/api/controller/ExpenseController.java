package com.martin.api.controller;

import com.martin.api.persistence.model.User;
import com.martin.api.service.IExpenseService;
import com.martin.api.util.dto.DeleteResponse;
import com.martin.api.util.dto.expense.ExpenseCustomRequest;
import com.martin.api.util.dto.expense.ExpenseRequest;
import com.martin.api.util.dto.expense.ExpenseResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ExpenseController {

  private final IExpenseService expenseService;

  @GetMapping
  public ResponseEntity<List<ExpenseResponse>> allExpenses(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.ok(expenseService.getAllExpenses(userId));
  }

  @GetMapping("/pastweek")
  public ResponseEntity<List<ExpenseResponse>> allExpensesPastWeek(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.ok(expenseService.getAllExpensesPastWeek(userId));
  }

  @GetMapping("/pastmonth")
  public ResponseEntity<List<ExpenseResponse>> allExpensesPastMonth(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.ok(expenseService.getAllExpensesPastMonth(userId));
  }

  @GetMapping("/past3months")
  public ResponseEntity<List<ExpenseResponse>> allExpensesLast3Months(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.ok(expenseService.getAllExpensesLast3Months(userId));
  }

  @GetMapping("/custom")
  public ResponseEntity<List<ExpenseResponse>> allExpensesCustom(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid ExpenseCustomRequest request) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity
        .ok(expenseService.getAllExpensesCustom(request.startDate(), request.endDate(), userId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long id) {
    return ResponseEntity.ok(expenseService.getExpenseById(id));
  }

  @PostMapping
  public ResponseEntity<ExpenseResponse> saveExpense(@RequestBody @Valid ExpenseRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(expenseService.saveExpense(request, userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExpenseResponse> saveExpense(
      @PathVariable Long id,
      @RequestBody @Valid ExpenseRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(expenseService.updateExpense(id, userId, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteExpense(
      @PathVariable Long id,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(expenseService.deleteExpense(id, userId));
  }

  private Long extractUserId(UserDetails userDetails) {
    return ((User) userDetails).getId();
  }
}
