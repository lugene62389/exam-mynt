package com.gcash.api.gateway.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseMessage {

    private String status;
    private String message;
    private String additionalMessage;

}
