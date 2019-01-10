package com.cargodelivery.service.calculateimpl;

import com.cargodelivery.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;

import static org.junit.Assert.*;

public class FreightPriceCalculatorTest {

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

    @Test
    public void getPrice() throws NoSuchFieldException, NoSuchMethodException {
        Order order = new Order.OrderBuilder()
                .setWeight(10)
                .build();
        FreightPriceCalculator calculator = new FreightPriceCalculator(connection);


    }
}