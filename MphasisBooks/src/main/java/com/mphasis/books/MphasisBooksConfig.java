package com.mphasis.books;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.mphasis.books.util.ReadPropertiesFile;
/*
 * Configuration class to instantiate beans for RestTemplate and Properties
 */
@Configuration
public class MphasisBooksConfig {

	@Bean
	RestTemplate restTemplete() {
		return new RestTemplate();
	}
	
	@Bean
	Properties properties() {
		return ReadPropertiesFile.readPropertiesFile("./src/main/resources/customMessages.properties");
	}
}
