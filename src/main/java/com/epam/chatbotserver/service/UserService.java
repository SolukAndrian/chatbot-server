package com.epam.chatbotserver.service;

import com.epam.chatbotserver.models.User;

import java.util.List;

public interface UserService {
    public void save(User user);

    public User findOne(int id);

    public User findByUsername(String username);

    public List<User> findAll();
}
