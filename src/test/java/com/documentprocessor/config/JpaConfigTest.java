package com.documentprocessor.config;

import org.junit.jupiter.api.Test;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class JpaConfigTest {
    @Test
    void shouldProvideUtcDateTimeProvider() {
        var config = new JpaConfig();
        var provider = config.utcDateTimeProvider();
        assertThat(provider).isInstanceOf(DateTimeProvider.class);
        var now = provider.getNow();
        assertThat(now).isPresent();
        assertThat(now.get()).isInstanceOf(LocalDateTime.class);
    }
}
