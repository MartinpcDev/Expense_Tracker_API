package com.martin.api.util.dto.expense;

import com.martin.api.persistence.model.Category;
import java.time.LocalDate;

public record ExpenseResponse(
    Long id,
    Double price,
    Category category,
    LocalDate date
) {

}
