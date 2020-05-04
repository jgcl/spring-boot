package com.application.repositories;

import com.application.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findFirstByEmail(String email);
    User findByEmail(String email);
}
