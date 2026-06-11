package com.example.patterns;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of UserRepository.
 * Demonstrates the Repository pattern with a concrete implementation.
 */
public class InMemoryUserRepository implements UserRepository {
    
    private final Map<String, User> users = new ConcurrentHashMap<>();
    
    @Override
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.put(user.id(), user);
        return user;
    }
    
    @Override
    public Optional<User> findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User id cannot be blank");
        }
        return Optional.ofNullable(users.get(id));
    }
    
    @Override
    public List<User> findAll() {
        return List.copyOf(users.values());
    }
    
    @Override
    public boolean deleteById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User id cannot be blank");
        }
        return users.remove(id) != null;
    }
    
    @Override
    public boolean existsById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("User id cannot be blank");
        }
        return users.containsKey(id);
    }
}
