package com.forumhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.awt.SystemColor.info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenApi customOpenApi(){
        return new OpenApi().info(new info).tittle("Api do Fórum").version("v1");
    }
}
