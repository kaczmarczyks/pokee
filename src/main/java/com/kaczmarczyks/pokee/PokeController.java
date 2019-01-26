package com.kaczmarczyks.pokee;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
class PokeController {

    private static final String message =
            "This service has been poked %d time%s since it's wake up on %s. Refresh page to poke again.";
    private static final AtomicLong counter = new AtomicLong(0);

    private StartupTimeService startupTimeService;

    @GetMapping
    String poke() {
        long value = counter.addAndGet(1);
        String addedS = value > 1 ? "s" : "";
        String startupTime = startupTimeService.getStartupTime()
                .atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME);

        return String.format(message,
                value,
                addedS,
                startupTime);
    }

}
