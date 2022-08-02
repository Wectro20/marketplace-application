package com.andrii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.andrii.controllers", "com.andrii.service", "com.andrii.exceptions","com.andrii.repository","com.andrii.config", "com.andrii.view"})
@EntityScan({"com.andrii.models"})
@EnableJpaRepositories("com.andrii.repository")

public class MarketplaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}
}
