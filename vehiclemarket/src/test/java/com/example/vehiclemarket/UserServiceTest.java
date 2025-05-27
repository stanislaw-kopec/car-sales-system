package com.example.vehiclemarket;

import com.example.vehiclemarket.entity.User;
import com.example.vehiclemarket.repository.UserRepository;
import com.example.vehiclemarket.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should return all users")
    void testGetAllUsers() {
        User user1 = new User();
        user1.setName("user1");

        User user2 = new User();
        user2.setName("user2");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Should return user if user exist")
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("user1");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("user1", foundUser.get().getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create a new user")
    void testCreateUser() {
        User user = new User();
        user.setName("newUser");

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("newUser", createdUser.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should delete an existing user")
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should update an existing user")
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("oldName");

        User updatedUser = new User();
        updatedUser.setName("newName");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        Optional<User> result = userService.updateUser(1L, updatedUser);

        assertTrue(result.isPresent());
        assertEquals("newName", result.get().getName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }
}
