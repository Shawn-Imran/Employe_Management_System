package com.empoloyemanagementsystem.EmpoloyeManagementSystem.service;

import com.empoloyemanagementsystem.EmpoloyeManagementSystem.model.User;
import com.empoloyemanagementsystem.EmpoloyeManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public User updateUser(User user) {
        return userRepository.save(user);

    }

    public String deleteUserById(int id) {
        userRepository.deleteById(id);
        return "User got deleted";
    }

    public User fetchUserLogin(String stid, String password) {
        return userRepository.findByEmailAndPassword(stid, password);
    }
    public User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
