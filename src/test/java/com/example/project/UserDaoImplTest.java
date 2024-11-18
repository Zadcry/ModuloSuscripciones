package com.example.project;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserDaoImplTest {

    @Mock
    private UserDaoImpl userDao; // Se usa mock para pruebas unitarias

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa mocks
    }

    @Test
    void testSaveUser() {
        User user = new User("1", "John Doe", "johndoe@gmail.com", "password123", "active");
        doNothing().when(userDao).saveUser(user);

        userDao.saveUser(user);

        verify(userDao, times(1)).saveUser(user);
    }

    @Test
    void testDeleteUser() {
        String userId = "1";
        doNothing().when(userDao).deleteUser(userId);

        userDao.deleteUser(userId);

        verify(userDao, times(1)).deleteUser(userId);
    }
}
