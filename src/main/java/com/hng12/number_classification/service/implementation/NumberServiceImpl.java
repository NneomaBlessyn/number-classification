package com.hng12.number_classification.service.implementation;

import com.hng12.number_classification.dto.ClassifyNumberResponse;
import com.hng12.number_classification.exceptions.ClassifyNumberException;
import com.hng12.number_classification.service.NumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class NumberServiceImpl implements NumberService {

    @Value("${fun-fact.base-url}")
    private String funFactBaseUrl;

    private final RestTemplate restTemplate;

    @Override
    public ClassifyNumberResponse classifyNumber(String number) {
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
        boolean isPrime = checkPrime(number);
        boolean isPerfect = checkPerfect(number);
        boolean isArmstrong = checkArmstrong(number);
        boolean isEven = (number % 2 == 0);

        Set<String> properties = new HashSet<>();
        if (isArmstrong && isEven) {
            properties.add("armstrong");
            properties.add("even");
        } else if (isArmstrong) {
            properties.add("armstrong");
            properties.add("odd");
        } else if (isEven) {
            properties.add("even");
        } else {
            properties.add("odd");
        }

        int digitSum = calculateDigitSum(number);
        String funFact = fetchFunFact(number);

        return new ClassifyNumberResponse(number, isPrime, isPerfect, properties, digitSum, funFact);
    }

    private boolean checkPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }

    private boolean checkPerfect(int n) {
        if (n < 2) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) sum += (n / i);
            }
        }
        return sum == n;
    }

    private boolean checkArmstrong(int n) {
        int temp = Math.abs(n);
        String numStr = String.valueOf(temp);
        int length = numStr.length();

        int sum = 0;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, length);
            temp /= 10;
        }
        return sum == Integer.parseInt(numStr);
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
        url.append(number).append("/math");
        try {
            return restTemplate.getForObject(url.toString(), String.class);
        } catch (Exception e) {
            log.error("Error fetching fun fact for number: {}", number, e);
            return "Fun fact could not be retrieved.";
        }
    }
}
