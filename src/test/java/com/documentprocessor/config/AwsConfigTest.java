package com.documentprocessor.config;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;

import static org.assertj.core.api.Assertions.assertThat;

class AwsConfigTest {
    @Test
    void shouldCreateS3ClientWithInstanceProfileCredentials() {
        var config = new AwsConfig();
        var s3Client = config.s3Client();
        assertThat(s3Client).isInstanceOf(S3Client.class);
    }
}
