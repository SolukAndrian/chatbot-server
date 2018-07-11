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
    public void sendKey(@RequestParam("email") String email) {
        mailService.send(email);
    }

    @GetMapping("/api/message/set/temp/password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setKey(@RequestParam("password") String password, @RequestParam("email") String email) {
        System.out.println(password+" "+email);
        User user = userService.findByUsername(email);
        user.setPassword(password);
        userService.save(user);
    }
}
