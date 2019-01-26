package com.kaczmarczyks.pokee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/")
class PokeController {

    private static final String message = "This service has been poked %d time%s.";
    private static final AtomicLong counter = new AtomicLong(0);

    @GetMapping
    String poke() {
        long value = counter.addAndGet(1);
        String addedS = value > 1 ? "s" : "";
        return String.format(message, value, addedS);
    }

}
