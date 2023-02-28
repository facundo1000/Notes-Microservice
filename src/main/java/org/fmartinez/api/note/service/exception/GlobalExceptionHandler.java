package org.fmartinez.api.note.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Error handlerNotFoundException(NotFoundException exception) {
        return switch (exception.getErrorType()) {
            case NOTE_NOT_FOUND -> Error.builder()
                    .message("NOTE_NOT_FOUND")
                    .code("NOTE_NOT_FOUND " + exception.getAdditionalInfo())
                    .build();
            case USER_ACCOUNT_NOT_FOUND -> Error.builder()
                    .message("USER_ACCOUNT_NOT_FOUND")
                    .code("USER_ACCOUNT_NOT_FOUND " + exception.getAdditionalInfo())
                    .build();
        };
    }

}


