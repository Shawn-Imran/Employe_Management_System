package com.empoloyemanagementsystem.EmpoloyeManagementSystem.repository;

import com.empoloyemanagementsystem.EmpoloyeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepo extends JpaRepository<User, Integer> {
    @Query
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);

    User findByUsername(String username);
}
