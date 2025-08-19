package com.sparkals.journalApp.service;

import com.sparkals.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
public class UserDetailServiceImplTests {
    //@Autowired
    @InjectMocks /*it allows us to only run this service without starting Spring ApplicationContext
   @InjectMock - create instances of this , and search for @Mock then injected it in*/
    private UserDetailServiceImpl userDetailService;

    //@Mock
    @Mock
    private UserRepository userRepository;

    @BeforeEach  //before every test run this method in which we will intialize the @mock
    public void setUp() {
        //UserRepository will be initialised and injected too
        MockitoAnnotations.initMocks(this);
    }

   /* void loadUserByUserNameTest() {
        // Arrange
        User mockUser = new User();
        mockUser.setusername("Ayush");
        mockUser.setassword("AYush");
        mockUser.setroles("USER");
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        // Act
        UserDetails userDetails = userDetailService.loadUserByUsername("Ayush");

        // Assert
        assertNotNull(userDetails);
        assertEquals("Ayush", userDetails.getUsername());
        assertEquals("AYush", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }
}*/
}
