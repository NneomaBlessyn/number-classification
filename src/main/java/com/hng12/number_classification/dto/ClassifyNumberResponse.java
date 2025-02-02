package com.hng12.number_classification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ClassifyNumberResponse {
    @JsonProperty("number")
    private int number;

    @JsonProperty("is_prime")
    private boolean isPrime;

    @JsonProperty("is_perfect")
    private boolean isPerfect;

    @JsonProperty("properties")
    private Set<String> properties;

    @JsonProperty("digit_sum")
    private int digitSum;

    @JsonProperty("fun_fact")
    private String funFact;
}
