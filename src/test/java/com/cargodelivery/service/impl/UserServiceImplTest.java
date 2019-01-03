package com.cargodelivery.service.impl;

import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.User;
import com.cargodelivery.repository.UserRepository;
import com.cargodelivery.repository.impl.UserRepositoryImpl;
import com.cargodelivery.validator.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private Connection connection;

    @Before
    public void init() {
        connection = Mockito.mock(Connection.class);
    }

//    @Test(expected = NoSuchDataException.class)
//    public void whenUserNullThenIncorrectInputException() throws NoSuchDataException {
//        UserRepository userRepository = new UserRepositoryImpl(connection);
//        UserServiceImpl userService = new UserServiceImpl(connection);
//        when(userRepository.findById(0)).thenReturn(null);
//        userService.getUserById(0);
//    }
}