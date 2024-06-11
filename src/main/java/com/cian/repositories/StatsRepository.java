package com.cian.repositories;

import com.cian.entities.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StatsRepository extends MongoRepository<Stats, String> {
    public Stats findStatsByComponent(String component);
}
