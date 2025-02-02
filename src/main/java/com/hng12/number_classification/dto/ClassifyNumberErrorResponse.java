package com.hng12.number_classification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassifyNumberErrorResponse {
    private String number;
    private boolean error;
}
