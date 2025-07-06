package com.example.userservice.repo;

import ch.qos.logback.core.model.INamedModel;
import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // Optional: findByEmail, findByPhoneNumber, etc.
}