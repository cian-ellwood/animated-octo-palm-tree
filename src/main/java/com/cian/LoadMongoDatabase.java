package com.cian;

import com.cian.entities.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* I opted to use Mongo purely to make accessing my data in a DB,
 with this I should hopefully bypass setting up grafana mimir.
 I'm running off 3 hours sleep however so lets see if i'll regret this :D */
@Configuration
class LoadMongoDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadMongoDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StatsRepository statsRepository){
        return args -> {
            log.info("Preloading " + statsRepository.save(new Stats("service1", 103311L, 200L, "application/json", "firefox")));
        };
    }
}
