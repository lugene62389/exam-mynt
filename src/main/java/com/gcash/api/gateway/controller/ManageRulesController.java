package com.gcash.api.gateway.controller;

import com.gcash.api.gateway.model.request.RequestDeliveryCost;
import com.gcash.api.gateway.model.request.RequestDeliveryCostUpdate;
import com.gcash.api.gateway.model.response.ResponseMessage;
import com.gcash.api.gateway.service.ManageRulesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Updating Manage Rules")
@RequestMapping("/api/v1/manage-rules")
public class ManageRulesController {

    @Autowired
    private ManageRulesService manageRulesService;

    @Operation(description = "The ruleName only accepts Reject, HeavyParcel, LargeParcel, MediumParcel, SmallParcel",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = RequestDeliveryCostUpdate.class))))
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResponseMessage>> getDeliveryCost(@Valid @RequestBody RequestDeliveryCostUpdate requestModel) {

        return manageRulesService.updateDeliveryCostRule(requestModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
