package com.epam.chatbotserver.service.impl;

import com.epam.chatbotserver.service.BotService;
import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImpl implements BotService {

  private static final String APP_ID = "";
  private static final String APP_PASSWORD = "";
  private final MicrosoftAppCredentials credentials;

  public BotServiceImpl() {
    this.credentials = new MicrosoftAppCredentials(APP_ID, APP_PASSWORD);
  }

  @Override
  public Activity interactWithBot(Activity activity) {
    if (activity != null) {
      if (activity.type().equals(ActivityTypes.MESSAGE)) {
          return new Activity()
                  .withType(ActivityTypes.MESSAGE)
                  .withText("Alica: " + activity.text());
      }
    } 
    return new Activity()
          .withType(ActivityTypes.MESSAGE)
          .withText("Error. Activity is null");
  }
}

