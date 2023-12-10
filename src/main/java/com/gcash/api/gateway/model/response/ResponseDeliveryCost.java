package com.gcash.api.gateway.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ResponseDeliveryCost {

    private String category;

    private String description;

    private BigDecimal deliveryCost;

}
