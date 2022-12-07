package com.cognizant.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.ApiInfo()).select().build();
	}
	
	private ApiInfo ApiInfo() {
		return new ApiInfoBuilder().title("Account Service").description("Mobile Device Service for Subscribers")
				.termsOfServiceUrl("http://www.cognizant.com").version("2.7").build();
	}
}
