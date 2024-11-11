package com.martin.api.util.dto.expense;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ExpenseCustomRequest(
    @NotNull(message = "startDate required")
    LocalDate startDate,
    @NotNull(message = "endDate required")
    LocalDate endDate
) {

}
