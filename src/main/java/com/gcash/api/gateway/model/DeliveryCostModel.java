package com.gcash.api.gateway.model;


import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.gcash.api.gateway.constant.DefaultConstant.*;

@Getter
@Setter
public class DeliveryCostModel {

    @DocumentId
    private String id;
    @PropertyName(CONDITION_COUNT)
    private BigDecimal conditionCount;
    @PropertyName(COST_MULTIPLIER)
    private BigDecimal costMultiplier;
    @PropertyName(DESCRIPTION)
    private String description;
    @PropertyName(PRIORITY)
    private Integer priority;
    @PropertyName(RULE_NAME)
    private String ruleName;

    private Boolean isExceedCondition;
    private Boolean isWeightCondition;


}
