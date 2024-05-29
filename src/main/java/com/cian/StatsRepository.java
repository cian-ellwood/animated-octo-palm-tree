package com.cian;

import com.cian.entities.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StatsRepository extends MongoRepository<Stats, String> {
    public Stats findByComponent(String component);
}
