package com.cargodelivery.service.impl;

import com.cargodelivery.model.User;
import com.cargodelivery.repository.UserRepository;
import com.cargodelivery.service.UserService;
import org.apache.log4j.Logger;

/**
 * Author : Volodymyr Hrinchenko
 */
public class UserServiceImpl implements UserService {
    /**
     * Logger log4j
     */
    final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public boolean existsUser(String login, String password) {
        return false;
    }
}
