package com.gcash.api.gateway.model.request;

import com.gcash.api.gateway.constant.Rule;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class RequestDeliveryCostUpdate {


    @NotNull
    private Rule ruleName;

    @DecimalMin(value = "0.01", message = "The cost multiplier value must be greater than 0.01")
    private BigDecimal costMultiplier;

    @DecimalMin(value = "0.01", message = "The condition count value must be greater than 0.01")
    private BigDecimal conditionCount;

    private Boolean isExceedCondition = null;
    private Boolean isWeightCondition = null;

    private Integer priority;

    private String description;
}
