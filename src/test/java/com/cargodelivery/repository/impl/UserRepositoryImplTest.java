package com.cargodelivery.repository.impl;

import com.cargodelivery.model.User;
import com.cargodelivery.repository.UserRepository;
import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserRepositoryImplTest {

    @NotNull
    private UserRepository userRepository;

    @NotNull
    private Connection connection;

    @Before
    public void before() {
        try {
            String userName = "root";
            String password = "1111";
            String connectionUrl = "jdbc:mysql://localhost:3306/cargodelivery?useSSL=true&characterEncoding=utf8";
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            userRepository = new UserRepositoryImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#findById(Integer)
     */
    @Test
    public void findUserByIdWhichExistThenReturnUser() {
        User user = userRepository.findById(1);
        User expectedUser = new User.Builder().setId(1).setName("Max").setSurname("Barski")
                .setCity("Vinnitsa").setPhone("+380672121212").setMail("bar@gmail.com")
                .setPassword("1111").build();
        assertEquals(user, expectedUser);
        assertEquals(user.getId(), expectedUser.getId());
        assertEquals(user.getName(), expectedUser.getName());
        assertEquals(user.getSurname(), expectedUser.getSurname());
        assertEquals(user.getCity(), expectedUser.getCity());
        assertEquals(user.getPhone(), expectedUser.getPhone());
    }
    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#findById(Integer)
     */
    @Test
    public void findUserByIdWhichIsNotExistThenReturnNull() {
        User user = userRepository.findById(0);
        assertNull(user);
    }

    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#findByLogin(String)
     */
    @Test
    public void findUserByLoginWhichExistThenReturnUser() {
        User user = userRepository.findByLogin("bar@gmail.com");
        User expectedUser = new User.Builder().setId(1).setName("Max").setSurname("Barski")
                .setCity("Vinnitsa").setPhone("+380672121212").setMail("bar@gmail.com")
                .setPassword("1111").build();
        assertEquals(user, expectedUser);
        assertEquals(user.getId(), expectedUser.getId());
        assertEquals(user.getName(), expectedUser.getName());
        assertEquals(user.getSurname(), expectedUser.getSurname());
        assertEquals(user.getCity(), expectedUser.getCity());
        assertEquals(user.getPhone(), expectedUser.getPhone());
    }
    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#findByLogin(String)
     */
    @Test
    public void findUserByLoginWhichIsNotExistThenReturnNull() {
        User user = userRepository.findByLogin("@gmail.com");
        assertNull(user);
    }

    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#existsUser(String, String)
     */
    @Test
    public void whenUserExistsReturnTrue(){
        boolean isExist = userRepository.existsUser("bar@gmail.com", "1111");
        assertTrue(isExist);
    }
    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#existsUser(String, String)
     */
    @Test
    public void whenNoSuchLoginReturnFalse(){
        boolean isExist = userRepository.existsUser("@gmail.com", "1111");
        assertFalse(isExist);
    }
    /**
     * @see com.cargodelivery.repository.impl.UserRepositoryImpl#existsUser(String, String)
     */
    @Test
    public void whenNoSuitablePasswordReturnFalse(){
        boolean isExist = userRepository.existsUser("bar@gmail.com", "1112");
        assertFalse(isExist);
    }
}
