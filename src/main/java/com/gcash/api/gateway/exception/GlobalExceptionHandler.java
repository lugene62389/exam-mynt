package com.gcash.api.gateway.exception;


import com.gcash.api.gateway.model.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMessage handleValidationExceptions(MethodArgumentNotValidException ex) {

        ResponseMessage.ResponseMessageBuilder response = ResponseMessage.builder();

        response.status(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.message(ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).toList().get(0));

        return response.build();
    }

}
