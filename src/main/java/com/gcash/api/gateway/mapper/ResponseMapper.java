package com.gcash.api.gateway.mapper;

import com.gcash.api.gateway.model.DeliveryCostModel;
import com.gcash.api.gateway.model.response.ResponseDeliveryCost;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class ResponseMapper {

    public ResponseDeliveryCost map (DeliveryCostModel deliveryCostModel, BigDecimal deliveryCost) {
        ResponseDeliveryCost.ResponseDeliveryCostBuilder responseBuilder = ResponseDeliveryCost.builder();
        responseBuilder.deliveryCost(deliveryCost);
        responseBuilder.category(deliveryCostModel.getRuleName());
        responseBuilder.description(deliveryCostModel.getDescription());

        return  responseBuilder.build();
    }
}
