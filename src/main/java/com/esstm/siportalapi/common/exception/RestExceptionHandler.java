// src/main/java/com/esstm/siportalapi/common/exception/RestExceptionHandler.java
package com.esstm.siportalapi.common.exception;

import com.esstm.siportalapi.common.dto.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(400))
                .body(new ErrorResponse(400, ex.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(ResponseStatusException ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        return ResponseEntity
                .status(statusCode)
                .body(new ErrorResponse(statusCode.value(), ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerError(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatusCode.valueOf(500))
                .body(new ErrorResponse(500, "서버에서 오류가 발생했습니다."));
    }
}
