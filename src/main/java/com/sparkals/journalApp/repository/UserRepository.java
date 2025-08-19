package com.sparkals.journalApp.repository;

import com.sparkals.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//inside MongoRepository many methods are defined already thats why we extend
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
