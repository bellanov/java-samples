package com.example.patterns;

/**
 * User entity representing a user in the system.
 * Demonstrates immutability and the Record pattern (Java 17+).
 */
public record User(String id, String name, String email) {
    
    public User {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User id cannot be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("User email cannot be blank");
        }
    }
    
    public User withName(String newName) {
        return new User(this.id, newName, this.email);
    }
    
    public User withEmail(String newEmail) {
        return new User(this.id, this.name, newEmail);
    }
}
