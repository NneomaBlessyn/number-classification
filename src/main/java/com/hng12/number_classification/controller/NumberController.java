package com.hng12.number_classification.controller;

import com.hng12.number_classification.dto.ClassifyNumberResponse;
import com.hng12.number_classification.service.NumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Number Classification", description = "Endpoints for classifying numbers and retrieving fun facts")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NumberController {

    private final NumberService numberService;

    @Operation(
            summary = "Classify Number",
            description = "Checks if the given number is prime, perfect, armstrong, odd/even, calculates its digit sum, and fetches a fun fact."
    )
    @GetMapping("/classify-number")
    public ClassifyNumberResponse classifyNumber(@RequestParam String number) {
        return numberService.classifyNumber(number);
    }

}
