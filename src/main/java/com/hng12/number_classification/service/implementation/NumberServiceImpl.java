package com.hng12.number_classification.service.implementation;

import com.hng12.number_classification.dto.ClassifyNumberResponse;
import com.hng12.number_classification.exceptions.ClassifyNumberException;
import com.hng12.number_classification.service.NumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.primes.Primes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NumberServiceImpl implements NumberService {

    @Value("${fun-fact.base-url}")
    private String funFactBaseUrl;

    @Value("${math.type:math}")
    private String mathType;

    private final RestTemplate restTemplate;

    @Override
    public ClassifyNumberResponse classifyNumber(String number) {
        log.info("Classifying number: {}", number);
        if(StringUtils.isBlank(number) || !number.matches("^-?\\d+$")) {
            throw new ClassifyNumberException(number, HttpStatus.BAD_REQUEST);
        }

        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ClassifyNumberException(number, HttpStatus.BAD_REQUEST);
        }
        return classifyNumber(parsedNumber);
    }

    private ClassifyNumberResponse classifyNumber(int number) {
        boolean isPrime = Primes.isPrime(number);
        boolean isPerfect = checkPerfect(number);
        boolean isArmstrong = checkArmstrong(number);
        boolean isEven = (number % 2 == 0);

        List<String> properties = new ArrayList<>();
        // added prime, perfect, armstrong, even/odd properties
        if (isPrime) {
            properties.add("prime");
        }
        if (isPerfect) {
            properties.add("perfect");
        }
        if (isArmstrong) {
            properties.add("armstrong");
        }
        properties.add(isEven ? "even" : "odd");

        int digitSum = calculateDigitSum(number);
        String funFact = fetchFunFact(number);

        return new ClassifyNumberResponse(number, isPrime, isPerfect, properties, digitSum, funFact);
    }

    private boolean checkPerfect(int number) {
        if (number < 2) {
            return false;
        }

        int sum = 1;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;

                int otherDivisor = number / i;
                if (otherDivisor != i) {
                    sum += otherDivisor;
                }
            }
        }
        return sum == number;
    }

    private boolean checkArmstrong(int number) {
        int numDigits = String.valueOf(number).length();
        int sum = 0;
        int temp = number;

        while (temp > 0) {
            int digit = temp % 10;
            sum += (int) Math.pow(digit, numDigits);
            temp /= 10;
        }

        return sum == number;
    }

    private int calculateDigitSum(int n) {
        int temp = Math.abs(n);
        int sum = 0;
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }
        return sum;
    }

    private String fetchFunFact(int number) {
        StringBuilder url = new StringBuilder(funFactBaseUrl);
        url.append(number).append('/').append(mathType);
        try {
            return restTemplate.getForObject(url.toString(), String.class);
        } catch (Exception e) {
            log.error("Error fetching fun fact for number: {}", number, e);
            return "Fun fact could not be retrieved.";
        }
    }
}
