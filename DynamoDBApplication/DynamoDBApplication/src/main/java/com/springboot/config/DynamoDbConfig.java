package com.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfig {

	@Value("${amazon.access.key}")
	private String awsAccessKey;
	
	@Value("${amazon.access.secret-key}")
	private String awsSecretKey;
	
	@Value("${amazon.region}")
	private String awsRegion;
	
	@Value("${amazon.end-point.url}")
	private String awsDynamoDBEndpoint;
	
	//Bir metodun urettigi bean Spring container tarafindan manage edilecek demek bu
	@Bean 
	public DynamoDBMapper mapper()
	{
		return new DynamoDBMapper(amazonDynamoDBConfig());
	}
	
	public AmazonDynamoDB amazonDynamoDBConfig()
	{
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndpoint, awsRegion) )
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey )))
				.build();
	}
}
