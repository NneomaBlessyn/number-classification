package com.hng12.number_classification.service;

import com.hng12.number_classification.dto.ClassifyNumberResponse;
import org.springframework.stereotype.Service;

@Service
public interface NumberService {
    ClassifyNumberResponse classifyNumber(String number);
}
