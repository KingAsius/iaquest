package com.botscrew.iaquest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class IaquestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IaquestApplication.class, args);
	}

	@Bean
	public DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("HH:mm");
	}
}
