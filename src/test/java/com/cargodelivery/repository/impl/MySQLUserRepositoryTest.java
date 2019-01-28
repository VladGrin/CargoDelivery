//package com.cargodelivery.repository.impl;
//
//import com.cargodelivery.model.User;
//import com.cargodelivery.repository.UserRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import static org.junit.Assert.*;
//
//public class MySQLUserRepositoryTest {
//
//    private UserRepository userRepository;
//
//    private Connection connection;
//
//    @Before
//    public void before() {
//        ResourceBundle resource = ResourceBundle.getBundle("database");
//        try {
//            String userName = resource.getString("db.user");
//            String password = resource.getString("db.password");
//            String connectionUrl = resource.getString("db.url");
//            connection = DriverManager.getConnection(connectionUrl, userName, password);
//            userRepository = new MySQLUserRepository();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @After
//    public void after() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @see MySQLUserRepository#findById(Integer)
//     */
//    @Test
//    public void findUserByIdWhichExistThenReturnUser() {
//        User user = userRepository.findById(1);
//        User expectedUser = new User.UserBuilder().setId(1).setName("Василий").setSurname("Маханенко")
//                .setCity("Киев").setPhone("+380674561234").setMail("bar@gmail.com")
//                .setPassword("e10adc3949ba59abbe56e057f20f883e").setRole(User.Role.USER).build();
//        assertEquals(user, expectedUser);
//        assertEquals(user.getId(), expectedUser.getId());
//        assertEquals(user.getName(), expectedUser.getName());
//        assertEquals(user.getSurname(), expectedUser.getSurname());
//        assertEquals(user.getCity(), expectedUser.getCity());
//        assertEquals(user.getPhone(), expectedUser.getPhone());
//    }
//
//    /**
//     * @see MySQLUserRepository#findById(Integer)
//     */
//    @Test
//    public void findUserByIdWhichIsNotExistThenReturnNull() {
//        User user = userRepository.findById(0);
//        assertNull(user);
//    }
//
//    /**
//     * @see MySQLUserRepository#findByLogin(String)
//     */
//    @Test
//    public void findUserByLoginWhichExistThenReturnUser() {
//        User user = userRepository.findByLogin("bar@gmail.com");
//        User expectedUser = new User.UserBuilder().setId(1).setName("Василий").setSurname("Маханенко")
//                .setCity("Киев").setPhone("+380674561234").setMail("bar@gmail.com")
//                .setPassword("e10adc3949ba59abbe56e057f20f883e").setRole(User.Role.USER).build();
//        assertEquals(user, expectedUser);
//        assertEquals(user.getId(), expectedUser.getId());
//        assertEquals(user.getName(), expectedUser.getName());
//        assertEquals(user.getSurname(), expectedUser.getSurname());
//        assertEquals(user.getCity(), expectedUser.getCity());
//        assertEquals(user.getPhone(), expectedUser.getPhone());
//    }
//
//    /**
//     * @see MySQLUserRepository#findByLogin(String)
//     */
//    @Test
//    public void findUserByLoginWhichIsNotExistThenReturnNull() {
//        User user = userRepository.findByLogin("@gmail.com");
//        assertNull(user);
//    }
//
//    /**
//     * @see MySQLUserRepository#existsUser(String, String)
//     */
//    @Test
//    public void whenUserExistsReturnTrue() {
//        boolean isExist = userRepository.existsUser("bar@gmail.com", "e10adc3949ba59abbe56e057f20f883e");
//        assertTrue(isExist);
//    }
//
//    /**
//     * @see MySQLUserRepository#existsUser(String, String)
//     */
//    @Test
//    public void whenNoSuchLoginReturnFalse() {
//        boolean isExist = userRepository.existsUser("@gmail.com", "1111");
//        assertFalse(isExist);
//    }
//
//    /**
//     * @see MySQLUserRepository#existsUser(String, String)
//     */
//    @Test
//    public void whenNoSuitablePasswordReturnFalse() {
//        boolean isExist = userRepository.existsUser("bar@gmail.com", "1112");
//        assertFalse(isExist);
//    }
//}
