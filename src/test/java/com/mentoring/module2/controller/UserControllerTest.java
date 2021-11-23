package com.mentoring.module2.controller;

import com.mentoring.module2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void getUserById() {

        final List<User> users = (List<User>) userController.getUserById(1).getModel().get("users");

        assertEquals(1, users.size());
        assertEquals(1, users.get(0));
    }
}