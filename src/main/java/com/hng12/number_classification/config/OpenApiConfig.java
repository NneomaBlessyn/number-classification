//package com.hng12.number_classification.config;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@OpenAPIDefinition(info = @Info(title = "Number Classification APIs", version = "v1", description = "Backend endpoints for number classification"),
//        security = @SecurityRequirement(name = "JWT"))
//@SecurityScheme(name = "JWT", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
//public class OpenApiConfig {
//
//    @Bean
//    public GroupedOpenApi api() {
//        return GroupedOpenApi.builder()
//                .group("hng12")
//                .pathsToMatch("/**")
//                .build();
//    }
//}
