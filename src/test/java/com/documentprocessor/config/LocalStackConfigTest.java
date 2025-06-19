package com.documentprocessor.config;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.assertj.core.api.Assertions.assertThat;

class LocalStackConfigTest {

    @Test
    void shouldCreateS3ClientWithLocalstackEndpoint() {
        var config = new LocalStackConfig();
        ReflectionTestUtils.setField(config, "localstackEndpoint", "http://localhost:4566");
        var s3Client = config.s3Client();
        assertThat(s3Client).isInstanceOf(S3Client.class);
    }

    @Test
    void shouldCreateSqsAsyncClientWithLocalstackEndpoint() {
        var config = new LocalStackConfig();
        ReflectionTestUtils.setField(config, "localstackEndpoint", "http://localhost:4566");
        var sqsAsyncClient = config.sqsAsyncClient();
        assertThat(sqsAsyncClient).isInstanceOf(SqsAsyncClient.class);
    }

    @Test
    void shouldCreateSnsAsyncClientWithLocalstackEndpoint() {
        var config = new LocalStackConfig();
        ReflectionTestUtils.setField(config, "localstackEndpoint", "http://localhost:4566");
        var snsAsyncClient = config.snsAsyncClient();
        assertThat(snsAsyncClient).isInstanceOf(SnsAsyncClient.class);
    }
}
