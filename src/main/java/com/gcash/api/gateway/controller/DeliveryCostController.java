package com.gcash.api.gateway.controller;


import com.gcash.api.gateway.model.request.RequestDeliveryCost;
import com.gcash.api.gateway.model.response.ResponseDeliveryCost;
import com.gcash.api.gateway.service.DeliveryCostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;



@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Calculating Delivery Cost")
@RequestMapping("/api/v1/delivery-cost")
public class DeliveryCostController {

    @Autowired
    private DeliveryCostService deliveryCostService;


    @Operation(description = "I made this POST mapping so it will compatible to Axios or Frontend Devs",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestDeliveryCost.class))))
    @PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResponseDeliveryCost>> getDeliveryCost(@Valid @RequestBody RequestDeliveryCost requestModel) {

        return deliveryCostService.getDeliveryCost(requestModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
