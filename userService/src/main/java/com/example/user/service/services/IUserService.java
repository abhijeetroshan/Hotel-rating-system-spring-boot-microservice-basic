package com.example.user.service.services;

import com.example.user.service.entities.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    //TODO Delete
    //TODO Update
}
