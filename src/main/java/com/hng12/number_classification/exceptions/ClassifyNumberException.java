package com.hng12.number_classification.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ClassifyNumberException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
