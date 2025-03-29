//package com.blog.demo.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContext;
//import java.util.List;
//import java.util.Arrays;
//
//@Configuration
//public class SwaggerConfig {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private ApiKey apiKeys(){
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//
//    private List<SecurityContext> securityContexts(){
//        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
//    }
//
//    private List<SecurityReference> sf(){
//
//        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
//        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Blog - Application")
//                        .description("<a href='https://github.com/sh1vnsd' target='_blank'>This project is Developed by - https://github.com/sh1vnsd</a>")
//                        .version("1.0")
//                        .contact(new Contact()
//                                .name("Me")
//                                .email("iamshivanshsood@gmail.com"))
//                        .termsOfService("Terms Of Service")
//                        .license(new License()
//                                .name("License of APIS")
//                                .url("API license URL"))
//                        .securityContexts(securityContexts())
//                        .securitySchemes(Arrays.asList(apiKeys()));
//    }
//}


package com.blog.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog - Application")
                        .description("<a href='https://github.com/sh1vnsd' target='_blank'>This project is Developed by - https://github.com/sh1vnsd</a>")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Shivansh Sood")
                                .email("iamshivanshsood@gmail.com"))
                        .termsOfService("Terms Of Service")
                        .license(new License()
                                .name("License of APIs")
                                .url("API license URL")))
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .name("JWT")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
