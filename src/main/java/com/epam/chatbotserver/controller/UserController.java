package com.epam.chatbotserver.controller;

import com.epam.chatbotserver.dto.UserDto;
import com.epam.chatbotserver.models.Message;
import com.epam.chatbotserver.models.User;
import com.epam.chatbotserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/api/registration")
  @ResponseStatus(HttpStatus.CREATED)
  public void getHome(@RequestBody UserDto userDto) {
    userService.save(new User(userDto.getUsername(), userDto.getPassword(), userDto.getFirstName(), userDto.getLastName()));
  }

  @PostMapping("/api/message")
  public Message createAnswer(@RequestBody String string) {
    return new Message(string);
  }
}
