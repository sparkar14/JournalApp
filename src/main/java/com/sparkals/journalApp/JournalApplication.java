package com.sparkals.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {
	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	//it handle the Transaction in the saveEntry
   @Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbfactory) {
		return new MongoTransactionManager(dbfactory);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

//PlatformTractionManager
//MongoTractionManager

//we can create beans in main class using method with the help of @SpringBootApplication

//MongoDataBaseFactory - helps in making connections with database