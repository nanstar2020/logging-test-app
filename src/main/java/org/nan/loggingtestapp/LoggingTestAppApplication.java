package org.nan.loggingtestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.nan")
@SpringBootApplication
public class LoggingTestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingTestAppApplication.class, args);
	}

}
