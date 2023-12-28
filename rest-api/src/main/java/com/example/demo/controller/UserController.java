package com.example.demo.controller;
import com.example.demo.UserAlreadyExistsException;
import com.example.demo.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
    private List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        if(userRepository.existsById(user.getId()))
            throw new UserAlreadyExistsException(user.getId());
        else
            userRepository.save(user);
        return "User created: " + user.getName() + " id:" + user.getId();
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("User id:" +id);
        User user = getUserById(id);
        if (userRepository.findAll().contains(user)) {
            userRepository.delete(user);
            return "User deleted: " + id;
        }
        return "User not found";
    }
}