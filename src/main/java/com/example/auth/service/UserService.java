package com.example.auth.service;

import com.example.auth.domain.Role;
import com.example.auth.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username , String roleName);
    User getUser(String username);
    List<User> getUsers();

}
