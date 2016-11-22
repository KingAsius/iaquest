package com.botscrew.iaquest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class IaquestApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(IaquestApplication.class, args);
	}

	@Bean
	public DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("HH:mm");
	}
}
