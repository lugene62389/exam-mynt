package com.gcash.api.gateway.model.request;


import com.gcash.api.gateway.model.MeasureModel;
import com.gcash.api.gateway.model.WeightModel;
import jakarta.validation.Valid;
import lombok.Getter;


@Getter
public class RequestDeliveryCost {

    @Valid
    private WeightModel weight;

    @Valid
    private MeasureModel height;

    @Valid
    private MeasureModel width;

    @Valid
    private MeasureModel length;

    private String voucherCode;

}
