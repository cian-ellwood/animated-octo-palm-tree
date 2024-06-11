package com.cian.apiController;

//We use AtomicLong to ensure that this can be used on a multithreaded application
import java.util.List;

import com.cian.repositories.LogsRepository;
import com.cian.entities.Logs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogsController {
    private final LogsRepository logsRepository;

    LogsController(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }
    /*TODO Make this Asynchronous,
      https://spring.io/guides/gs/async-method
     */
    @GetMapping("/logs")
    List<Logs> all(){
        return logsRepository.findAll();
    }
    @PostMapping("/logs")
    Logs newLog(@RequestBody Logs newLog) {
        return logsRepository.save(newLog);
    }
}
