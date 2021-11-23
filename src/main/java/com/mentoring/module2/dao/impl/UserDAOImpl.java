package com.mentoring.module2.dao.impl;

import com.mentoring.module2.dao.UserDAO;
import com.mentoring.module2.model.User;
import com.mentoring.module2.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private Storage storage;

    private static final Random random = new Random();

    @Override
    public User getUserById(final long userId) {
        return storage.getUsers().get(String.valueOf(userId));
    }

    @Override
    public Collection<User> getAllUsers() {
        return storage.getUsers().values();
    }

    @Override
    public User createUser(final User user) {

        long userId = random.nextLong();

        while (getUserById(userId) != null) {
            userId = random.nextLong();
        }

        user.setId(userId);

        storage.getUsers().put(String.valueOf(user.getId()), user);

        return user;
    }

    @Override
    public User updateUser(final User user) {
        return storage.getUsers().computeIfPresent(String.valueOf(user.getId()), (k, v) -> user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        return Objects.nonNull(storage.getUsers().remove(String.valueOf(userId)));
    }
}
