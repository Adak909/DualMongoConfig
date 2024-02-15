package io.adak.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = { "io.adak.primary.repository" }, mongoTemplateRef = "primaryMongoTemplate")
@EnableConfigurationProperties
public class PrimaryMongoConfigg {

	@Primary
	@Bean(name = "primaryProperties")
	@ConfigurationProperties(prefix = "spring.data.mongodb")
	public MongoProperties primaryProperties() {
		return new MongoProperties();
	}
	
	@Primary
	@Bean(name = "primaryMongoClient")
	public MongoClient mongoClient(@Qualifier("primaryProperties") MongoProperties mongoProperties) {
		return MongoClients.create(mongoProperties.getUri());
	}
	
	@Primary
	@Bean(name = "primaryMongoDBFactory")
	public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("primaryMongoClient") MongoClient mongoClient,
			@Qualifier("primaryProperties") MongoProperties mongoProperties) {
		return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
	}
	
	@Primary
	@Bean(name = "primaryMongoTemplate")
	public MongoTemplate mongoTemplate(@Qualifier("primaryMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
		return new MongoTemplate(mongoDatabaseFactory);
	}
}
