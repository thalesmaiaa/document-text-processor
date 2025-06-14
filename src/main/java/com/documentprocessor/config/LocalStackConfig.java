package com.documentprocessor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Profile("local")
@Configuration
public class LocalStackConfig {

    @Value("${spring.cloud.aws.endpoint.uri}")
    private String localstackEndpoint;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create(localstackEndpoint))
                .forcePathStyle(true)
                .build();
    }
}
