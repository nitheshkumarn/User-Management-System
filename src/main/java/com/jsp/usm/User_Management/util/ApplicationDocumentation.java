package com.jsp.usm.User_Management.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;






@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	
	Contact contact() {
		return new Contact().email("nitheshkn012@gmail.com")
				.url("google.com")
				.name("Nithesh Kumar N");
		
	}
	

	Info info() {
		return new Info()
				.title("User ManagemeNt API")
				.version("1.0v")
				.description("user Management system is a RESTful API built using" + " Spring boot and MySql database")
				.contact(contact());
		
	}
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}

}
