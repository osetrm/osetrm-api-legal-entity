package com.osetrm.api.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record ErrorResponse(@JsonInclude(JsonInclude.Include.NON_NULL) String uuid, List<ErrorMessage> errorMessages) {

    public ErrorResponse(List<ErrorMessage> errorMessages) {
        this(null, errorMessages);
    }

    public ErrorResponse(String uuid, ErrorMessage errorMessage) {
        this(uuid, List.of(errorMessage));
    }

    public ErrorResponse(ErrorMessage errorMessage) {
        this(null, List.of(errorMessage));
    }

    public record ErrorMessage(@JsonInclude(JsonInclude.Include.NON_NULL) String path, String message) {
        public ErrorMessage(String message) {
            this(null, message);
        }
    }

}
