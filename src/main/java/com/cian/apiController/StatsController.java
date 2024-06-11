package com.cian.apiController;

//We use AtomicLong to ensure that this can be used on a multithreaded application
import java.util.List;

import com.cian.repositories.StatsRepository;
import com.cian.entities.Stats;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatsController {
    private final StatsRepository statsRepository;

    StatsController(StatsRepository statsRepository){
        this.statsRepository = statsRepository;
    }

    /*TODO Make this Asynchronous,
      https://spring.io/guides/gs/async-method
     */

    @GetMapping("/stats")
    List<Stats> all(){
        return statsRepository.findAll();
    }
    @PostMapping("/stats")
    Stats newStat(@RequestBody Stats newStat) {
        return statsRepository.save(newStat);
    }
}
