package com.epam.chatbotserver.controller;

import com.epam.chatbotserver.service.BotService;
import com.microsoft.bot.schema.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BotController {

  @Autowired
  private BotService botService;

  @PostMapping("/api/messages")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Activity sendResponseToBot(@RequestBody Activity activity) {
    return botService.interactWithBot(activity);
  }
}
