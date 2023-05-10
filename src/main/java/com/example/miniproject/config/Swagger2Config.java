package com.example.miniproject.config;

import com.example.miniproject.jwt.JwtUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("mini-project")
                .description("Api Description");

        String token_header = JwtUtil.AUTHORIZATION_HEADER;

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(token_header);

        Components components = new Components()
                .addSecuritySchemes(token_header, new SecurityScheme()
                        .name(token_header)
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
