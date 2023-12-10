package com.gcash.api.gateway.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MeasureModel {

    @NotNull(message = "The measure value is required")
    @DecimalMin(value = "0.01", message = "The measure value must be greater than 0.01")
    private BigDecimal value;

    @NotNull(message = "The measure unit is required")
    @Schema(description = "The measure unit must be 'cm' or 'centimeter'", example = "cm", required = true)
    @Pattern(regexp = "(?i)cm|centimeter", message = "The measure unit must be 'cm' or 'centimeter'")
    private String unit;
}
