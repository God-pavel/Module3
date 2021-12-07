package com.mentoring.module3.service.impl;

import com.mentoring.module3.model.impl.User;
import com.mentoring.module3.repository.UserRepository;
import com.mentoring.module3.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserById(final long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        final Pageable page = PageRequest.of(pageNum, pageSize);
        return userRepository.findAllByNameContains(name, page);
    }

    @Override
    public User createUser(final User user) {
        if (getUserByEmail(user.getEmail()) != null) {
            LOGGER.warn("Email {} is not unique", user.getEmail());
            throw new IllegalArgumentException();
        }
        LOGGER.info("Creating user with email {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final User user) {
        LOGGER.info("Updating user with id {}", user.getId());
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        LOGGER.info("Deleting user with id {}", userId);
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
