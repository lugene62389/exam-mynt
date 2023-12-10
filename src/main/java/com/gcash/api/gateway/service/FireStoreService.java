package com.gcash.api.gateway.service;


import com.gcash.api.gateway.model.DeliveryCostModel;
import com.gcash.api.gateway.model.request.RequestDeliveryCostUpdate;
import com.gcash.api.gateway.model.response.ResponseMessage;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.gcash.api.gateway.constant.DefaultConstant.*;

@Slf4j
@Service
public class FireStoreService {

    private final Firestore firestore;

    public FireStoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<DeliveryCostModel> getDeliveryCostRules ()  {

        try {
            // Retrieve all rules by "Priority" order
            CollectionReference collection = firestore.collection(FIRESTORE_COLLECTION);
            Query query = collection.orderBy(PRIORITY, Query.Direction.ASCENDING);
            QuerySnapshot querySnapshot = query.get().get();

            return querySnapshot.getDocuments().stream()
                    .map(doc -> doc.toObject(DeliveryCostModel.class))
                    .toList();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseMessage updateDeliveryCost (RequestDeliveryCostUpdate request) {

        ResponseMessage.ResponseMessageBuilder response = ResponseMessage.builder();

        Firestore db = FirestoreOptions.getDefaultInstance().getService();

        DocumentReference docRef = db.collection(FIRESTORE_COLLECTION).document(request.getRuleName().name());

        Map<String, Object> updates = new HashMap<>();

        if (Objects.nonNull(request.getCostMultiplier())) {
            updates.put(COST_MULTIPLIER, request.getCostMultiplier());
        }
        if (Objects.nonNull(request.getConditionCount())) {
            updates.put(CONDITION_COUNT, request.getConditionCount());
        }
        if (Objects.nonNull(request.getDescription())) {
            updates.put(DESCRIPTION, request.getDescription());
        }
        if (Objects.nonNull(request.getIsWeightCondition())) {
            updates.put(IS_WEIGHT_CONDITION, request.getIsWeightCondition().toString());
        }
        if (Objects.nonNull(request.getIsExceedCondition())) {
            updates.put(IS_EXCEED_CONDITION, request.getIsExceedCondition().toString());
        }
        if (Objects.nonNull(request.getPriority())) {
            updates.put(PRIORITY, request.getPriority());
        }

        if(updates.isEmpty()) {
            response.status("No Action");
            response.message("No field has been updated.");
        } else {
            ApiFuture<WriteResult> writeResult = docRef.set(updates, SetOptions.merge());
            String message = request.getRuleName().name().concat(" document has been successfully updated.");
            response.status("Success");
            response.message(message);
        }
        return response.build();
    }

}
