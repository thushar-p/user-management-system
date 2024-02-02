package com.jsp.ums.util;

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
		return new Contact()
				.email("thusharkulal8@gmail.com")
				.url("www.instagram.com/thushxr/")
				.name("Thushar");
	}
	
	
	Info info() {
		return new Info()
				.title("User Management System API")
				.version("1.0v")
				.description("User Management System is RESTful API built using Spring Boot and MySQL database")
				.contact(contact());
	}

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
	
}
