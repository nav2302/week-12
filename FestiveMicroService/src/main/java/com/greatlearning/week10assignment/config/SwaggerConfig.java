package com.greatlearning.week10assignment.config;

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
	
	public static final String OrderController_TAG = "Order service";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.greatlearning.week10assignment.controller")).build()
				
				.tags(new Tag(OrderController_TAG, "Please use code \"FESTIVECODE\" for getting 50% discount !!!!"));
				
	}

}
