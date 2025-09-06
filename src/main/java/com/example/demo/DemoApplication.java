package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableMongoAuditing
@EnableTransactionManagement
public class DemoApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
}
