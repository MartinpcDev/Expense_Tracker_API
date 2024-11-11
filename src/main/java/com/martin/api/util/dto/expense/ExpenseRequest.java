package com.martin.api.util.dto.expense;

import com.martin.api.persistence.model.Category;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record ExpenseRequest(
    @NotNull(message = "El price no puede ir vacio")
    @DecimalMin(value = "10.0", message = "El precio debe ser al menos 10.0")
    @DecimalMax(value = "999.9", message = "El precio no debe superar 999.9")
    @Digits(integer = 3, fraction = 1, message = "El precio debe tener hasta 3 dígitos enteros y 1 decimal")
    Double price,
    @NotNull(message = "La categoria no puede ir vacio")
    Category category
) {

}
