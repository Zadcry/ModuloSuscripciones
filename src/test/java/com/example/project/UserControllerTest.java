package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

class UserControllerTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User("1", "John Doe", "johndoe@gmail.com", "password123", "active");
        when(userDao.getUserById("1")).thenReturn(CompletableFuture.completedFuture(user));

        CompletableFuture<User> result = userController.getUserById("1");

        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getSubscriptionStatus(), result.get().getSubscriptionStatus());
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(
            new User("1", "John Doe", "johndoe@gmail.com", "password123", "active"),
            new User("2", "Jane Doe", "janedoe@gmail.com", "password123", "inactive")
        );
        when(userDao.getAllUsers()).thenReturn(CompletableFuture.completedFuture(users));

        CompletableFuture<List<User>> result = userController.getAllUsers();

        assertEquals(2, result.get().size());
    }

    @Test
    void testSaveUser() {
        User user = new User("1", "John Doe", "johndoe@gmail.com", "password123");
        doNothing().when(userDao).saveUser(user);

        userController.saveUser(user);

        verify(userDao, times(1)).saveUser(user);
    }

    @Test
    void testDeleteUser() {
        String userId = "1";
        doNothing().when(userDao).deleteUser(userId);

        userController.deleteUser(userId);

        verify(userDao, times(1)).deleteUser(userId);
    }
}
