package com.cargodelivery.repository;

import com.cargodelivery.model.User;

public interface UserRepository {
    boolean save(User user);
    User findById(Integer id);
    User findByLogin(String login);
    boolean existsUser(String login, String password);

}
