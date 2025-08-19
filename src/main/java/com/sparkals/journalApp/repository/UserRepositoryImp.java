package com.sparkals.journalApp.repository;

import com.sparkals.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImp {

    @Autowired
    private MongoTemplate mongoTemplate;

    //will get the all the user Oppt for sentimental analysis
    //we will using Criteria and Query

    public List<User> getUserForSA(){
        Query query = new Query();
        //Criteria is like adding condition for accessing the perticular data in Database
      //  query.addCriteria(Criteria.where("userName").is(""));
      //  query.addCriteria(Criteria.where("age").gte(20));

        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"));
        //query.addCriteria(Criteria.where("email").exists(true));
      //  query.addCriteria(Criteria.where("email").ne(null).ne(""));

        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));


        List<User> users =   mongoTemplate.find(query, User.class);
    return users;
    }

}
