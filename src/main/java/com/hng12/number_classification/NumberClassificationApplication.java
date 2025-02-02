package com.hng12.number_classification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Number Classification API",
				version = "1.0",
				description = "API that classifies numbers as prime, perfect, armstrong, odd/even, digit sum, and fetches a fun fact."
		)
)
@SpringBootApplication
public class NumberClassificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumberClassificationApplication.class, args);
	}

}
