package com.epam.chatbotserver.controller;

import com.epam.chatbotserver.models.User;
import com.epam.chatbotserver.service.BotService;
import com.epam.chatbotserver.utility.IntentTypes;
import com.epam.chatbotserver.utility.UserCache;
import com.epam.chatbotserver.utility.UserDetails;
import com.microsoft.bot.schema.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class BotController {

  @Autowired
  private BotService botService;

  @PostMapping("/api/messages")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Activity sendResponseToBot(@RequestBody Activity activity) throws IOException, ClassNotFoundException {
    UserCache userCache = UserCache.getInstance();
    User user;
    if (userCache.containsUser(activity.recipient().id())){
      user = userCache.getObject(activity.recipient().id());
    }else {
      user = new User();
      user.setIdInBotSystem(activity.recipient().id());
      user.setUsername(activity.recipient().name());
      user.setIntentType(IntentTypes.HELLO);
    }
    userCache.putObject(user);
    UserDetails userDetails = new UserDetails(activity, user);
    return botService.interactWithBot(userDetails);
  }
}
