package com.kaczmarczyks.pokee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
class TestClockConfiguration {

    static final String MOCK_POKE_TIME = "2019-01-01T10:00:00Z";
    static final String TIMEZONE = "UTC";

    @Bean
    @Primary
    Clock pokeClock() {
        return Clock.fixed(Instant.parse(MOCK_POKE_TIME), ZoneId.of(TIMEZONE));
    }
}
