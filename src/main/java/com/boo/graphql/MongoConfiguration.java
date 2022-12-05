package com.boo.graphql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

//@Configuration
//@EnableMongoRepositories(basePackages = "com.boo.graphql.*")
public class MongoConfiguration { //extends AbstractMongoClientConfiguration {
	
//	@Value("${boo.graphql.mongodb.name}")
//	private String dbName;
//
//	@Override
//    public MongoClient mongoClient() {
//        final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/" + dbName);
//        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//            .applyConnectionString(connectionString)
//            .build();
//        return MongoClients.create(mongoClientSettings);
//    }
//
//	@Override
//	protected String getDatabaseName() {
//		return dbName;
//	}
}
