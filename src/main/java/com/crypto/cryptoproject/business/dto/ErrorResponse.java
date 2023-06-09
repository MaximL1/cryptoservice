package com.crypto.cryptoproject.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends SimpleResponse {

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        error(error);
    }

}
