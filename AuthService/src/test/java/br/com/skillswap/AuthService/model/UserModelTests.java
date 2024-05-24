package br.com.skillswap.AuthService.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserModelTests {

    private User mockedUser = mock(User.class);

    @BeforeEach
    public void newUser(){
        when(mockedUser.getEmail()).thenReturn("rafael@gmail.com");
        when(mockedUser.getUsername()).thenReturn("rafael26733");
        when(mockedUser.getPassword()).thenReturn("teste123");
    }

    @Test
    public void hasEmail(){
        assertEquals("rafael@gmail.com", mockedUser.getEmail());
    }

    @Test
    public void hasUsername(){
        assertEquals("rafael26733", mockedUser.getUsername());
    }

    @Test
    public void hasPassword(){
        assertTrue(mockedUser.getPassword().length() >= 8);
    }
}
