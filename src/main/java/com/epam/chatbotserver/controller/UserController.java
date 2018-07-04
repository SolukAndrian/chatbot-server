package com.epam.chatbotserver.controller;

import com.epam.chatbotserver.dto.UserDto;
import com.epam.chatbotserver.models.User;
import com.epam.chatbotserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getHome(@RequestBody UserDto userDto) {
        userService.save(User.entityToUser(userDto));
        return "home";
    }
}
