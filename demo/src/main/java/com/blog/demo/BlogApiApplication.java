package com.blog.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //This annotation proivde Configuration classes annotation features
public class BlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}


	//After doing this implementation we can use ModelMapper in our project
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
