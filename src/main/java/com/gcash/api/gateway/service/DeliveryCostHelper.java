package com.gcash.api.gateway.service;


import com.gcash.api.gateway.model.DeliveryCostModel;
import com.gcash.api.gateway.model.request.RequestDeliveryCost;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DeliveryCostHelper {

    public BigDecimal getVolumetricWeight (BigDecimal height, BigDecimal width, BigDecimal length) {
        return height.multiply(width).multiply(length);
    }

    public boolean conditionWithOperators (BigDecimal parcelMultiplier,
                                            DeliveryCostModel deliveryCostModel) {

        if (deliveryCostModel.getIsExceedCondition()) { // Use greater than as operator
            return parcelMultiplier.compareTo(deliveryCostModel.getConditionCount()) > 0;
        }

        // Use less than as operator
        return parcelMultiplier.compareTo(deliveryCostModel.getConditionCount()) < 0;

    }

    public BigDecimal getParcelMultiplier (RequestDeliveryCost request,
                                            Boolean isWeightCondition) {
        return isWeightCondition ?
                request.getWeight().getValue() :
                getVolumetricWeight(
                        request.getHeight().getValue(),
                        request.getWidth().getValue(),
                        request.getLength().getValue()
                );
    }

    public BigDecimal getDeliveryCost (BigDecimal parcelMultiplier, BigDecimal costMultiplier) {
        return parcelMultiplier.multiply(costMultiplier);
    }
}
