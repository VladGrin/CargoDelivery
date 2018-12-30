package com.cargodelivery.service;

import com.cargodelivery.model.User;

public interface UserService {
    boolean saveUser(User user);
    User getUserById(Integer id);
    User getUserByLogin(String login);
    boolean existsUser(String login, String password);
}
