package com.cargodelivery.service.impl;

import com.cargodelivery.exception.DataAlreadyExistsException;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.User;
import com.cargodelivery.repository.UserRepository;
import com.cargodelivery.repository.impl.UserRepositoryImpl;
import com.cargodelivery.service.UserService;
import com.cargodelivery.validator.Validator;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Author : Volodymyr Hrinchenko
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(Connection connection) {
        this.userRepository = new UserRepositoryImpl(connection);
    }

    @Override
    public boolean saveUser(String name, String surname, String city, String phone,
                         String login, String password, User.Role role) throws IncorrectInputException, DataAlreadyExistsException {
        Validator.validateName(name);
        Validator.validateName(surname);
        Validator.validateName(city);
        Validator.validateMobileNumber(phone);
        Validator.validateEmailAddress(login);

        if(existsUser(login, password)){
            throw new DataAlreadyExistsException("Such user already exists");
        }

        User user = new User.UserBuilder().setName(name)
                .setSurname(surname).setCity(city).setPhone(phone)
                .setMail(login).setPassword(password).setRole(role).build();

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) throws NoSuchDataException {

        User userById = userRepository.findById(id);
        if (userById == null){
            throw new NoSuchDataException("No user by id " + id);
        }
        return userById;
    }

    @Override
    public User getUserByLogin(String login) throws NoSuchDataException {
        User userByLogin = userRepository.findByLogin(login);
        if (userByLogin == null){
            throw new NoSuchDataException("No such user: " + userByLogin);
        }
        return userByLogin;
    }

    @Override
    public boolean existsUser(String login, String password) throws IncorrectInputException {
        Validator.validateEmailAddress(login);
        return userRepository.existsUser(login, password);
    }
}
