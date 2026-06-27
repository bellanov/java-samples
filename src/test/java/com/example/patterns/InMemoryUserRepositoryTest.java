package com.example.patterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("InMemoryUserRepository Tests")
class InMemoryUserRepositoryTest {
    
    private UserRepository repository;
    
    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
    }
    
    @Test
    @DisplayName("Should save user and retrieve it")
    void testSaveAndFind() {
        User user = new User("user1", "John Doe", "john@example.com");
        User saved = repository.save(user);
        
        assertThat(saved).isEqualTo(user);
        Optional<User> found = repository.findById("user1");
        assertThat(found).isPresent().contains(user);
    }
    
    @Test
    @DisplayName("Should throw exception when saving null user")
    void testSaveNullUserThrowsException() {
        assertThatThrownBy(() -> repository.save(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("cannot be null");
    }
    
    @Test
    @DisplayName("Should return empty optional when user not found")
    void testFindByIdNotFound() {
        Optional<User> found = repository.findById("nonexistent");
        
        assertThat(found).isEmpty();
    }
    
    @Test
    @DisplayName("Should return all users")
    void testFindAll() {
        User user1 = new User("user1", "John Doe", "john@example.com");
        User user2 = new User("user2", "Jane Doe", "jane@example.com");
        
        repository.save(user1);
        repository.save(user2);
        
        List<User> users = repository.findAll();
        
        assertThat(users).hasSize(2).contains(user1, user2);
    }
    
    @Test
    @DisplayName("Should delete user by id")
    void testDeleteById() {
        User user = new User("user1", "John Doe", "john@example.com");
        repository.save(user);
        
        boolean deleted = repository.deleteById("user1");
        
        assertThat(deleted).isTrue();
        assertThat(repository.findById("user1")).isEmpty();
    }
    
    @Test
    @DisplayName("Should return false when deleting non-existent user")
    void testDeleteByIdNotFound() {
        boolean deleted = repository.deleteById("nonexistent");
        
        assertThat(deleted).isFalse();
    }
    
    @Test
    @DisplayName("Should check if user exists")
    void testExistsById() {
        User user = new User("user1", "John Doe", "john@example.com");
        repository.save(user);
        
        assertThat(repository.existsById("user1")).isTrue();
        assertThat(repository.existsById("nonexistent")).isFalse();
    }
    
    @Test
    @DisplayName("Should throw exception with blank user id")
    void testBlankUserIdThrowsException() {
        assertThatThrownBy(() -> repository.findById(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("cannot be blank");
    }
}
