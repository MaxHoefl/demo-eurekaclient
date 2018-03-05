package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.ExcludeFromTests;

//@Profile("test")
//@Configuration
//@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.*"}, 
//			   excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromTests.class))
//@EnableJpaRepositories("com.example.demo.dao")
//@EntityScan("com.example.demo.resource")
//@ComponentScan(basePackageClasses= {TestDemoAppConfig.class})
public class IntegrationTestApplication 
{
	public IntegrationTestApplication()
	{
		
	}
	
//	public static void main(String[] args) {
//		SpringApplication.run(IntegrationTestApplication.class, args);
//	}
}
