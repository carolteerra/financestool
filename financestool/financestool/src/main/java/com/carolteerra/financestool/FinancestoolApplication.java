package com.carolteerra.financestool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.beans.JavaBean;

@SpringBootApplication
@ComponentScan("com.carolteerra.financestool.model.repositories")
public class FinancestoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancestoolApplication.class, args);
	}

}
