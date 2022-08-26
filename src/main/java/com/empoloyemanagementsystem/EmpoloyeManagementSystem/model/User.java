package com.empoloyemanagementsystem.EmpoloyeManagementSystem.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
//    @Column(unique = true)
    private String email;
    private String address;
    private String birthday;
    private String gender;
    private String password;
}
