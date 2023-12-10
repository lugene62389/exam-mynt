package com.gcash.api.gateway.service;

import com.gcash.api.gateway.model.request.RequestDeliveryCostUpdate;
import com.gcash.api.gateway.model.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ManageRulesService {

    @Autowired
    FireStoreService fireStoreService;

    public Mono<ResponseMessage> updateDeliveryCostRule (RequestDeliveryCostUpdate request) {

        var response = fireStoreService.updateDeliveryCost(request);

        return Mono.just(response);
    }
}
