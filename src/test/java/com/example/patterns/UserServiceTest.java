package com.example.patterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("UserService Tests")
class UserServiceTest {
    
    private UserRepository repository;
    private UserService service;
    
    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
        service = new UserService(repository);
    }
    
    @Test
    @DisplayName("Should create a new user successfully")
    void testCreateUser() {
        User user = service.createUser("user1", "John Doe", "john@example.com");
        
        assertThat(user).isNotNull();
        assertThat(user.id()).isEqualTo("user1");
        assertThat(user.name()).isEqualTo("John Doe");
        assertThat(user.email()).isEqualTo("john@example.com");
    }
    
    @Test
    @DisplayName("Should throw exception when creating duplicate user")
    void testCreateDuplicateUserThrowsException() {
        service.createUser("user1", "John Doe", "john@example.com");
        
        assertThatThrownBy(() -> service.createUser("user1", "Jane Doe", "jane@example.com"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("already exists");
    }
    
    @Test
    @DisplayName("Should get user by id")
    void testGetUserById() {
        service.createUser("user1", "John Doe", "john@example.com");
        User user = service.getUserById("user1");
        
        assertThat(user.id()).isEqualTo("user1");
        assertThat(user.name()).isEqualTo("John Doe");
    }
    
    @Test
    @DisplayName("Should throw UserNotFoundException when user not found")
    void testGetUserByIdNotFound() {
        assertThatThrownBy(() -> service.getUserById("nonexistent"))
            .isInstanceOf(UserNotFoundException.class)
            .hasMessageContaining("not found");
    }
    
    @Test
    @DisplayName("Should get all users")
    void testGetAllUsers() {
        service.createUser("user1", "John Doe", "john@example.com");
        service.createUser("user2", "Jane Doe", "jane@example.com");
        
        List<User> users = service.getAllUsers();
        
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::id).contains("user1", "user2");
    }
    
    @Test
    @DisplayName("Should update user email")
    void testUpdateUserEmail() {
        service.createUser("user1", "John Doe", "john@example.com");
        User updated = service.updateUserEmail("user1", "john.new@example.com");
        
        assertThat(updated.email()).isEqualTo("john.new@example.com");
        assertThat(updated.name()).isEqualTo("John Doe");
    }
    
    @Test
    @DisplayName("Should delete user")
    void testDeleteUser() {
        service.createUser("user1", "John Doe", "john@example.com");
        boolean deleted = service.deleteUser("user1");
        
        assertThat(deleted).isTrue();
        assertThatThrownBy(() -> service.getUserById("user1"))
            .isInstanceOf(UserNotFoundException.class);
    }
    
    @Test
    @DisplayName("Should return false when deleting non-existent user")
    void testDeleteNonExistentUser() {
        boolean deleted = service.deleteUser("nonexistent");
        
        assertThat(deleted).isFalse();
    }
    
    @Test
    @DisplayName("Should throw exception when UserRepository is null")
    void testConstructorWithNullRepository() {
        assertThatThrownBy(() -> new UserService(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("cannot be null");
    }
}
