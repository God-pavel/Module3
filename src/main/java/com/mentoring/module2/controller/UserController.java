package com.mentoring.module2.controller;

import com.mentoring.module2.facade.BookingFacade;
import com.mentoring.module2.model.User;
import com.mentoring.module2.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.addObject("users", bookingFacade.getUsersByName(name, size, number));
        return modelAndView;
    }

    @PostMapping
    public User createUser(@RequestParam("name") final String name,
                           @RequestParam("email") final String email) throws Exception {
        if (true) {
            throw new Exception();
        }
        return bookingFacade.createUser(UserImpl.builder().name(name).email(email).build());
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable("id") final long id,
                           @RequestParam("name") final String name,
                           @RequestParam("email") final String email) {
        return bookingFacade.createUser(UserImpl.builder().id(id).name(name).email(email).build());
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") final long id) {
        return bookingFacade.deleteUser(id);
    }

    @ExceptionHandler(Exception.class)
    public String controllerError() {

        return "Controller error";
    }
}
