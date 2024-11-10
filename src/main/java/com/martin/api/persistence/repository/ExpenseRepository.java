package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
