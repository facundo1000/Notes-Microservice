package org.fmartinez.api.note.service.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private final ErrorType errorType;

    private String additionalInfo;

    public NotFoundException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public NotFoundException(ErrorType errorType, String additionalInfo) {
        this.errorType = errorType;
        this.additionalInfo = additionalInfo;
    }
}
