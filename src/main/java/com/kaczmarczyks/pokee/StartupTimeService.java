package com.kaczmarczyks.pokee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
class StartupTimeService implements ApplicationListener<ContextRefreshedEvent> {

    private final Clock pokeClock;

    private LocalDateTime startupTime;

    LocalDateTime getStartupTime() {
        return startupTime;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        setStartupTime();
    }

    synchronized private void setStartupTime() {
        if (startupTime == null) {
            startupTime = LocalDateTime.now(pokeClock);
            log.debug("Pokee startup time is set up to {}", startupTime);
        }
    }
}
