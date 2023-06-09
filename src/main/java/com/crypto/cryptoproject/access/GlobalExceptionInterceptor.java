package com.crypto.cryptoproject.access;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crypto.cryptoproject.business.dto.ErrorResponse;
import com.crypto.cryptoproject.business.dto.SimpleResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionInterceptor extends ResponseEntityExceptionHandler {

    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request)
    {
        logException(ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.initializeFieldMessage();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> processError(error, errorResponse));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = {InvalidParameterException.class})
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException ex)
    {
        ErrorResponse errorRes = new ErrorResponse(ex.getMessage());
        logException(ex, errorRes);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
    }

    private void processError(ObjectError error, ErrorResponse errorResponse)
    {
        if (error.getArguments() == null)
        {
            return;
        }
        Arrays.stream(error.getArguments())
                .filter(MessageSourceResolvable.class::isInstance)
                .map(MessageSourceResolvable.class::cast)
                .forEach(messageSourceResolvable ->
                        errorResponse.result.fieldMessages.put(messageSourceResolvable.getDefaultMessage(),
                                List.of(new SimpleResponse.Result.ResponseMessage(error.getDefaultMessage()))));
    }

    protected void logException(Exception e)
    {
        logException(e, null);
    }

    protected void logException(Exception e, ErrorResponse error)
    {
        if (error != null)
        {
            error.result.logid = UUID.randomUUID().toString();
            log.error("handling exception, logid: '{}'", error.result.logid, e);
        }
        else
        {
            log.debug("handling exception", e);
        }
    }
}
