package com.epam.chatbotserver.controller;

import com.epam.chatbotserver.dto.UserDto;
import com.epam.chatbotserver.models.User;
import com.epam.chatbotserver.service.MailService;
import com.epam.chatbotserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/api/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDto userDto) {
        userService.save(new User(userDto));
    }

    @GetMapping("/api/message/key")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendKey(@RequestParam("email") String email){
        mailService.send(email);
    }

}
