package com.cashwu.todolist;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cash
 * http://localhost:9100/swagger-ui/index.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(info())
                .externalDocs(externalDocs());
    }

    private Info info() {
        return new Info()
                .title("todo api")
                .description("my todo api desc")
                .version("v1.0")
                .license(new License()
                        .name("MIT"));
    }

    private ExternalDocumentation externalDocs() {
        return new ExternalDocumentation()
                .description("another doc desc");
    }
}