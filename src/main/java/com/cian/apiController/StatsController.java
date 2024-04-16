package com.cian.apiController;

//We use AtomicLong to ensure that this can be used on a multithreaded application
import java.util.concurrent.atomic.AtomicLong;

import com.cian.records.statsRecord;
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
    public statsRecord stats(
            //@RequestParam(name = "loadTime", required = false) long loadTime,
            @RequestParam(name = "status") long status,
            @RequestParam(name = "server", defaultValue = "Firefox") String contentType,
            @RequestParam(name = "contentType", defaultValue = "application/json") String server)
            {
        //We return a new Stats Service Object, request Params are mapped to the statsRecord
        return new statsRecord(counter.incrementAndGet(), status, contentType, server);
    }
}
