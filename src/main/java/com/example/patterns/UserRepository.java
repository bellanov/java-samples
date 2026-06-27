package com.example.patterns;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface demonstrating the Repository pattern.
 * Abstracts data access logic from business logic.
 */
public interface UserRepository {
    
    /**
     * Save a user to the repository.
     * @param user the user to save
     * @return the saved user
     */
    User save(User user);
    
    /**
     * Find a user by id.
     * @param id the user id
     * @return Optional containing the user if found
     */
    Optional<User> findById(String id);
    
    /**
     * Find all users.
     * @return list of all users
     */
    List<User> findAll();
    
    /**
     * Delete a user by id.
     * @param id the user id
     * @return true if deleted, false if not found
     */
    boolean deleteById(String id);
    
    /**
     * Check if a user exists by id.
     * @param id the user id
     * @return true if user exists, false otherwise
     */
    boolean existsById(String id);
}
