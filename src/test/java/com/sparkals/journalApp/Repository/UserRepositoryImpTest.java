package com.sparkals.journalApp.Repository;

import com.sparkals.journalApp.repository.UserRepositoryImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

@SpringBootTest
public class UserRepositoryImpTest {

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Test
    public void testSaveUser() {
     Assertions.assertNotNull(userRepositoryImp.getUserForSA());
    }
}
