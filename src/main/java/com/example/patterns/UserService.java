package com.example.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * UserService demonstrates dependency injection and the Service pattern.
 * Business logic is separated from data access through the UserRepository interface.
 */
public class UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    
    /**
     * Constructor demonstrating dependency injection.
     * The service depends on UserRepository abstraction, not concrete implementation.
     * @param repository the UserRepository to use
     */
    public UserService(UserRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("UserRepository cannot be null");
        }
        this.repository = repository;
    }
    
    /**
     * Create and save a new user.
     * @param id the user id
     * @param name the user name
     * @param email the user email
     * @return the created user
     * @throws IllegalArgumentException if user already exists
     */
    public User createUser(String id, String name, String email) {
        LOGGER.info("Creating user with id: {}", id);
        
        if (repository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " already exists");
        }
        
        User user = new User(id, name, email);
        User saved = repository.save(user);
        LOGGER.info("User created successfully: {}", id);
        return saved;
    }
    
    /**
     * Get a user by id.
     * @param id the user id
     * @return the user if found
     * @throws UserNotFoundException if user not found
     */
    public User getUserById(String id) {
        LOGGER.info("Fetching user with id: {}", id);
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }
    
    /**
     * Get all users.
     * @return list of all users
     */
    public List<User> getAllUsers() {
        LOGGER.info("Fetching all users");
        return repository.findAll();
    }
    
    /**
     * Update a user's email.
     * @param id the user id
     * @param newEmail the new email
     * @return the updated user
     * @throws UserNotFoundException if user not found
     */
    public User updateUserEmail(String id, String newEmail) {
        LOGGER.info("Updating email for user: {}", id);
        
        User user = getUserById(id);
        User updated = user.withEmail(newEmail);
        repository.save(updated);
        LOGGER.info("User email updated: {}", id);
        return updated;
    }
    
    /**
     * Delete a user by id.
     * @param id the user id
     * @return true if deleted, false if not found
     */
    public boolean deleteUser(String id) {
        LOGGER.info("Deleting user with id: {}", id);
        boolean deleted = repository.deleteById(id);
        if (deleted) {
            LOGGER.info("User deleted: {}", id);
        }
        return deleted;
    }
}
