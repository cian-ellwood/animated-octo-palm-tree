package com.cian.repositories;

import com.cian.entities.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface LogsRepository extends MongoRepository<Logs, String> {
    public Logs findLogsByService(String service);
}
