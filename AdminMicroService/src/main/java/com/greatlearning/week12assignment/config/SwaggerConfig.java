package com.greatlearning.week12assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	public static final String SalesController_TAG = "Sales service";
	public static final String UserController_TAG = "User service";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.greatlearning.week12assignment.controller")).build()
				
				.tags(new Tag(SalesController_TAG, "REST APIs for getting SALES for current day and this MONTH only ADMIN user!!!!"))
				
				.tags(new Tag(UserController_TAG, "REST APIs for CRUD ops on USER only ADMIN allowed!!!!"));
				
	}

}
