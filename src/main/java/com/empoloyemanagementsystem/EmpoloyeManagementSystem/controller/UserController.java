package com.empoloyemanagementsystem.EmpoloyeManagementSystem.controller;

import com.empoloyemanagementsystem.EmpoloyeManagementSystem.model.AuthRequest;
import com.empoloyemanagementsystem.EmpoloyeManagementSystem.model.User;
import com.empoloyemanagementsystem.EmpoloyeManagementSystem.service.UserService;
import com.empoloyemanagementsystem.EmpoloyeManagementSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;





    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws Exception{
        User tempuser = null;
        String tempEmail = user.getEmail();
        
        if(tempEmail != null) {
            User obj = userService.fetchUserByEmail(tempEmail);
            if(obj != null) {
                System.out.println("User already exists with email: " + tempEmail);
                throw new Exception("User with " +tempEmail+" is already usered!!");
            }
            else {

                tempuser = userService.createUser(user);
                System.out.println("User Added Successfully with email: " + tempEmail);
            }
            
        
        }
        else {
            tempuser = null;
            System.out.println("Email is empty");
            throw new Exception("Email is empty");
        }


        return tempuser;
    }

    @PostMapping("/login")
//    @CrossOrigin(origins = "http://localhost:4200")
    public User loginUser(@RequestBody User user) throws Exception{
        String tempEmail = user.getEmail();
        String tempPass = user.getPassword();
        User obj = null;
        if(tempEmail != null && tempPass != null) {
            obj = userService.fetchUserLogin(tempEmail, tempPass);
        }
        if(obj == null) {
            throw new Exception("Invalid User!!!");
        }
        return obj;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PutMapping("/updateuserbyid/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/userEmail")
    public User getUserByEmail(@RequestBody User user) {
        return userService.fetchUserByEmail(user.getEmail());
    }





    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

}
