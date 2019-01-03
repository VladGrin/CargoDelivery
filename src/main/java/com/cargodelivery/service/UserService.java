package com.cargodelivery.service;

import com.cargodelivery.exception.DataAlreadyExistsException;
import com.cargodelivery.exception.IncorrectInputException;
import com.cargodelivery.exception.NoSuchDataException;
import com.cargodelivery.model.User;

public interface UserService {
    boolean saveUser(String name, String surname, String city, String phone,
                  String login, String password, User.Role role) throws IncorrectInputException, DataAlreadyExistsException;

    User getUserById(Integer id) throws NoSuchDataException;

    User getUserByLogin(String login) throws NoSuchDataException;

    boolean existsUser(String login, String password) throws IncorrectInputException;
}
