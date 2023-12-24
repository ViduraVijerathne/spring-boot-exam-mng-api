package com.example.exammngapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)//disable spring security
public class ExamMngApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamMngApiApplication.class, args);
	}

//	model mapper auto mapping
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
