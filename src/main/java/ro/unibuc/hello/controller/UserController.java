package ro.unibuc.hello.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.unibuc.hello.data.user.User;
import ro.unibuc.hello.data.user.UserDTO;
import ro.unibuc.hello.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private Counter uniqueUserCounter;
    private Counter totalUserCounter;

    public UserController(MeterRegistry registry) {
        uniqueUserCounter = registry.counter("users.unique");
        totalUserCounter = registry.counter("users.total");
    }

    @PostMapping("/api/users")
    @ResponseBody
    public void postUser(@RequestBody UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
            uniqueUserCounter.increment();
            totalUserCounter.increment();
        } catch (Exception E) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public User getUser(@PathVariable String id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            if (e.getMessage().equals(HttpStatus.NOT_FOUND.toString())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry it's not working");
            }
        }
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable String id) {
        try {
            userService.deleteUserById(id);
            totalUserCounter.increment(-1);
        } catch (Exception e) {
            if (e.getMessage().equals(HttpStatus.NOT_FOUND.toString())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server on fire probably");
            }
        }
    }
}
