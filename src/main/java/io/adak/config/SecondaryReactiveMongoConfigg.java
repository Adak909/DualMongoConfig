//package io.adak.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//
//@Configuration
//@EnableReactiveMongoRepositories(basePackages = {
//		"io.adak.reactive.repository" }, reactiveMongoTemplateRef = "secondaryMongoTemplate")
//@EnableConfigurationProperties
//public class SecondaryReactiveMongoConfigg {
//
//	@Bean(name = "secondaryProperties")
//	@ConfigurationProperties(prefix = "mongodb")
//	public MongoProperties secondaryProperties() {
//		return new MongoProperties();
//	}
//
//	@Bean(name = "secondaryMongoClient")
//	public MongoClient mongoClient(@Qualifier("secondaryProperties") MongoProperties mongoProperties) {
//		return MongoClients.create(mongoProperties.getUri());
//	}
//
//	@Bean(name = "secondaryMongoDBFactory")
//	public ReactiveMongoDatabaseFactory mongoDatabaseFactory(@Qualifier("secondaryMongoClient") MongoClient mongoClient,
//			@Qualifier("secondaryProperties") MongoProperties mongoProperties) {
//		return new SimpleReactiveMongoDatabaseFactory(new ConnectionString(mongoProperties.getUri()));
//	}
//
//	@Bean(name = "secondaryMongoTemplate")
//	public ReactiveMongoTemplate reactiveMongoTemplate(
//			@Qualifier("secondaryMongoDBFactory") ReactiveMongoDatabaseFactory mongoDatabaseFactory) {
//		return new ReactiveMongoTemplate(mongoDatabaseFactory);
//	}
//}
