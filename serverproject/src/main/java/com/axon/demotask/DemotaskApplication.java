package com.axon.demotask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemotaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemotaskApplication.class, args);
	}

	@Bean
	public BlogpostRepository getBlogpostRepositoryBean(){
		return new BlogpostRepository();
	}
}