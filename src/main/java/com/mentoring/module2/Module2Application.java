package com.mentoring.module2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Module2Application {

	public static void main(String[] args) {
		SpringApplication.run(Module2Application.class, args);
	}

}
