package com.gcash.api.gateway.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WeightModel {

    @NotNull(message = "The weight value is required")
    @DecimalMin(value = "0.01", message = "The weight value must be greater than 0.01")
    private BigDecimal value;

    @NotNull(message = "The weight unit is required")
    @Schema(description = "The weight unit must be 'kg' or 'kilogram'", example = "kg", required = true)
    @Pattern(regexp = "(?i)kg|kilogram", message = "The weight unit must be 'kg' or 'kilogram'")
    private String unit;
}
