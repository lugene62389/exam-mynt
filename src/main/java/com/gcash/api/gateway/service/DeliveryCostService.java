package com.gcash.api.gateway.service;


import com.gcash.api.gateway.mapper.ResponseMapper;
import com.gcash.api.gateway.model.DeliveryCostModel;
import com.gcash.api.gateway.model.request.RequestDeliveryCost;
import com.gcash.api.gateway.model.request.RequestDeliveryCostUpdate;
import com.gcash.api.gateway.model.response.ResponseDeliveryCost;
import com.gcash.api.gateway.model.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryCostService {

    @Autowired
    FireStoreService fireStoreService;
    @Autowired
    ResponseMapper responseMapper;
    @Autowired
    DeliveryCostHelper deliveryCostHelper;

    public Mono<ResponseDeliveryCost> getDeliveryCost(RequestDeliveryCost request) {

        List<DeliveryCostModel> deliveryCostModelList = fireStoreService.getDeliveryCostRules();

        var response = getComputedDeliveryCost(request, deliveryCostModelList);

        return Mono.just(response);
    }

    private ResponseDeliveryCost getComputedDeliveryCost (RequestDeliveryCost request,
                                                          List<DeliveryCostModel> deliveryCostModelList) {

        boolean metCondition = false;
        DeliveryCostModel deliveryCostModel;
        BigDecimal parcelMultiplier;

        // Checks all rules except the least priority
        for (int priority = 0; priority < deliveryCostModelList.size() - 1; priority++) {

            deliveryCostModel = deliveryCostModelList.get(priority);

            parcelMultiplier = deliveryCostHelper.getParcelMultiplier(request, deliveryCostModel.getIsWeightCondition());

            metCondition = deliveryCostHelper.conditionWithOperators(parcelMultiplier, deliveryCostModel);

            if(metCondition) {
                return responseMapper.map(deliveryCostModel,
                        deliveryCostHelper.getDeliveryCost(parcelMultiplier, deliveryCostModel.getCostMultiplier()));
            }

        }

        // If all rules above didn't met then it falls to the least priority category.
        deliveryCostModel = deliveryCostModelList.get(deliveryCostModelList.size() - 1);
        parcelMultiplier = deliveryCostHelper.getParcelMultiplier(request, deliveryCostModel.getIsWeightCondition());
        return responseMapper.map(deliveryCostModel,
                deliveryCostHelper.getDeliveryCost(parcelMultiplier, deliveryCostModel.getCostMultiplier()));
    }

}
