package com.sparkals.journalApp.service;

import com.sparkals.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
@Autowired
private UserRepository userRepository;
    @Disabled
    @Test
    public void testAdd(){
        assertEquals(4 , 2+2);
    }
@Test
    public void testFindByUserName(){
       assertNotNull( userRepository.findByUserName("Isha"));
    }
    @ParameterizedTest
    @CsvSource({"Ayush" , "Isha","ram"
    })
    public void testFindByUserName(String name){
        assertNotNull( userRepository.findByUserName(name));
    }

    @Disabled
 @ParameterizedTest
 @CsvSource({"3","1","2",
         "2","2","5",
         "19","9","10"
 })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
