package com.application.repositories;

import com.application.entities.Bot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BotRepository extends MongoRepository<Bot, String> {
    Optional<Bot> findByIdentifier(String identifier);
    Bot findFirstByOrderByIdAsc();
}
