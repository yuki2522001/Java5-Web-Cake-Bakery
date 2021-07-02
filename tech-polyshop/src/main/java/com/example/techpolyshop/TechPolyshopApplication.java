package com.example.techpolyshop;

import com.example.techpolyshop.config.StorageProperties;
import com.example.techpolyshop.service.StorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TechPolyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechPolyshopApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args->{
			storageService.init();
		});
	}
}
