package com.cian.apiController;

//We use AtomicLong to ensure that this can be used on a multithreaded application
import java.util.concurrent.atomic.AtomicLong;

import com.cian.endpoint.statsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class StatsController {
    private final AtomicLong counter = new AtomicLong();
    /*TODO Make this Asynchronous,
      https://spring.io/guides/gs/async-method
     */
    @GetMapping("/stats")
    public statsService stats(
            @RequestParam(name = "loadTime") long loadTime,
            @RequestParam(name = "status") long status,
            @RequestParam(name = "server") String server,
            @RequestParam(name = "contentType") String contentType) {
        //We return a new Stats Service Object, request Params are mapped to the statsService template
        return new statsService(counter.incrementAndGet(), loadTime, status, server, contentType);
    }
}
