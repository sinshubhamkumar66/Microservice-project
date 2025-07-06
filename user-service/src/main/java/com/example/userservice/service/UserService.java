package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.globalException.UserNotFoundException;
import com.example.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repo;

    @Autowired
    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(User user) {// ensure it's a new user
        return repo.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existing = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existing.setName(updatedUser.getName());
        existing.setAge(updatedUser.getAge());
        existing.setEmail(updatedUser.getEmail());
        existing.setPhoneNumber(updatedUser.getPhoneNumber());

        return repo.save(existing);
    }

    public User getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public void deleteUser(Long id) {
        if (!repo.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        repo.deleteById(id);
    }
}