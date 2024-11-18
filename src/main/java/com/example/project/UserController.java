package com.example.project;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/{id}")
    public CompletableFuture<User> getUserById(@PathVariable String id) {
        return userDao.getUserById(id);
    }

    @GetMapping
    public CompletableFuture<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userDao.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userDao.deleteUser(id);
    }
}
