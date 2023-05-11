package com.aminivan.pager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@OpenAPIDefinition
public class PagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagerApplication.class, args);
	}

}
