package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackageClasses= {TestDemoAppConfig.class})
public class IntegrationTestApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(IntegrationTestApplication.class, args);
	}
}
