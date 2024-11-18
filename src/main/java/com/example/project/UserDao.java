package com.example.project;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserDao {
    CompletableFuture<User> getUserById(String id);
    CompletableFuture<List<User>> getAllUsers();
    void saveUser(User user);
    void deleteUser(String id);
}
