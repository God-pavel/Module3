package com.mentoring.module2.dao;

import com.mentoring.module2.model.User;

import java.util.Collection;

public interface UserDAO {
    
    User getUserById(long userId);
    Collection<User> getAllUsers();
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(long userId);
}
