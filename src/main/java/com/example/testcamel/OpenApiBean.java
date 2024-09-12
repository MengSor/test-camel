package com.example.testcamel;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiBean {
    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("My Api")
                        .version("1.0")
                        .description("This is my API")
                );
    }
}
