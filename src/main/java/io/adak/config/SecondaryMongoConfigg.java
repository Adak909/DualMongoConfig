package io.adak.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = { "io.adak.secondary.repository" }, mongoTemplateRef = "secondaryMongoTemplate")
@EnableConfigurationProperties
public class SecondaryMongoConfigg {

	@Bean(name = "secondaryProperties")
	@ConfigurationProperties(prefix = "mongodb")
	public MongoProperties secondaryProperties() {
		return new MongoProperties();
	}

	@Bean(name = "secondaryMongoClient")
	public MongoClient mongoClient(@Qualifier("secondaryProperties") MongoProperties mongoProperties) {
		return MongoClients.create(mongoProperties.getUri());
	}

	@Bean(name = "secondaryMongoDBFactory")
	public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("secondaryMongoClient") MongoClient mongoClient,
			@Qualifier("secondaryProperties") MongoProperties mongoProperties) {
		return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
	}

	@Bean(name = "secondaryMongoTemplate")
	public MongoTemplate mongoTemplate(
			@Qualifier("secondaryMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
		return new MongoTemplate(mongoDatabaseFactory);
	}
}
