package com.mentoring.module2.service.impl;

import com.mentoring.module2.dao.UserDAO;
import com.mentoring.module2.model.User;
import com.mentoring.module2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private static Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public User getUserById(final long userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userDAO.getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        return userDAO.getAllUsers().stream()
                .filter(user -> user.getName().contains(name))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(final User user) {
        if (getUserByEmail(user.getEmail()) != null) {
            LOGGER.warn("Email {} is not unique", user.getEmail());
            throw new IllegalArgumentException();
        }
        LOGGER.info("Creating user with email {}", user.getEmail());
        return userDAO.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        LOGGER.info("Updating user with id {}", user.getId());
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        LOGGER.info("Deleting user with id {}", userId);
        return userDAO.deleteUser(userId);
    }
}
