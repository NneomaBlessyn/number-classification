package com.hng12.number_classification.exceptions;

import com.hng12.number_classification.dto.ClassifyNumberErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ClassifyNumberException.class)
    public ResponseEntity<ClassifyNumberErrorResponse> handleClassifyNumberException(ClassifyNumberException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ClassifyNumberErrorResponse(exception.getMessage(), true));
    }
}
