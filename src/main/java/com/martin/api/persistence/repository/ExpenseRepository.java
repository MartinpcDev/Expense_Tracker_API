package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.Expense;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);

  List<Expense> findAllByUserId(Long userId);

  @Query("SELECT e FROM Expense e WHERE e.date >= :dateInit AND e.date <= :dateFinish AND e.user"
      + ".id = :userId")
  List<Expense> findAllExpensesWithinDateRange(@Param("dateInit") LocalDate dateInit, @Param(
      "dateFinish") LocalDate dateFinish, @Param("userId") Long userId);
}
