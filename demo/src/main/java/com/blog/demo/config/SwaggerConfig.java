package com.blog.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog - Application")
                        .description("<a href='https://github.com/sh1vnsd' target='_blank'>This project is Developed by - https://github.com/sh1vnsd</a>")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Me")
                                .email("iamshivanshsood@gmail.com"))
                        .termsOfService("Terms Of Service")
                        .license(new License()
                                .name("License of APIS")
                                .url("API license URL")));
    }
}
