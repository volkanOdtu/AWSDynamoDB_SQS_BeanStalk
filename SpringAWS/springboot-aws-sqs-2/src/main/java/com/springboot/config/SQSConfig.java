package com.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SQSConfig {

	@Value("${cloud.aws.region.static}")	
	private String region;
	
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccessKey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;
	

	@Value("${cloud.aws.end-point.uri}")		
	private String sqsEndPoint;

	@Bean
	public QueueMessagingTemplate queueMessagingTemplate()
	{
		return new QueueMessagingTemplate(amazonSQSAsync());
	}
	
	public AmazonSQSAsync amazonSQSAsync()
	{
		return AmazonSQSAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsEndPoint, region) )
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey )))
				.build();

	}
	
	
}
