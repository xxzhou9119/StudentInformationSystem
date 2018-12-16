package com.amazonaws.lambda.demo.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConnector {

	 static AmazonDynamoDB dynamoDb ;
 
	 public static void init() {
		if (dynamoDb == null) {
//		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		 DefaultAWSCredentialsProviderChain credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
		credentialsProvider.getCredentials();
		
		
		dynamoDb = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion("us-east-1")
					.build();		
		System.out.println("I created the client");
		} 

	}
	 
	 public AmazonDynamoDB getClient() {
		 return dynamoDb;
	 }
}
