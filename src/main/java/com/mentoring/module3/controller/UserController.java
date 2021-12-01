package com.mentoring.module3.controller;

import com.mentoring.module3.facade.BookingFacade;
import com.mentoring.module3.model.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/{id}")
    public ModelAndView getUserById(@PathVariable("id") final long id) {
        final ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", List.of(bookingFacade.getUserById(id)));
        return modelAndView;
    }

    @GetMapping(params = {"email"})
    public User getUserByEmail(@RequestParam("email") final String email) {
        return bookingFacade.getUserByEmail(email);
    }

    @GetMapping(params = {"name", "size", "number"})
    public ModelAndView getUsersByName(@RequestParam("name") final String name,
                                       @RequestParam("size") final int size,
                                       @RequestParam("number") final int number) {
        final ModelAndView modelAndView = new ModelAndView("users");
        final List<User> users = bookingFacade.getUsersByName(name, size, number);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping
    public User createUser(@RequestParam("name") final String name,
                           @RequestParam("email") final String email,
                           @RequestParam("money") final long money) {
        return bookingFacade.createUser(User.builder().name(name).moneyAmount(BigDecimal.valueOf(money)).email(email).build());
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable("id") final long id,
                           @RequestParam("name") final String name,
                           @RequestParam("email") final String email,
                           @RequestParam("money") final long money) {
        return bookingFacade.updateUser(User.builder().id(id).name(name).moneyAmount(BigDecimal.valueOf(money)).email(email).build());
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") final long id) {
        return bookingFacade.deleteUser(id);
    }
}
