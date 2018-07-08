package com.epam.chatbotserver.service.impl;

import com.epam.chatbotserver.service.BotService;
import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class BotServiceImpl implements BotService {

  private static final String APP_ID = "";
  private static final String APP_PASSWORD = "";
  private final MicrosoftAppCredentials credentials;

  @Value("${request.greeting1}")
  private String greetingReq1;
  @Value("${request.greeting2}")
  private String greetingReq2;
  @Value("${request.greeting3}")
  private String greetingReq3;
  @Value("${request.greeting4}")
  private String greetingReq4;

  @Value("${response.greeting1}")
  private String greetingResp1;
  @Value("${response.greeting2}")
  private String greetingResp2;
  @Value("${response.greeting3}")
  private String greetingResp3;
  @Value("${response.greeting4}")
  private String greetingResp4;

  private List<String> greetingReq;
  private List<String> greetingResp;

  public BotServiceImpl() {
    this.credentials = new MicrosoftAppCredentials(APP_ID, APP_PASSWORD);
  }


  private void initValues() {
    this.greetingReq = new ArrayList<>(Arrays.asList(
            greetingReq1, greetingReq2, greetingReq3, greetingReq4));
    this.greetingResp = new ArrayList<>(Arrays.asList(
            greetingResp1, greetingResp2, greetingResp3, greetingResp4));
  }

  private String randomResponse(){
    Random random = new Random();
    return greetingResp.get(random.nextInt(greetingResp.size()));
  }

  @Override
  public Activity interactWithBot(Activity activity) {
    initValues();
    if (activity != null) {
      if (activity.type().equals(ActivityTypes.MESSAGE)) {
        if (greetingReq.contains(activity.text())) {
          return new Activity()
                  .withType(ActivityTypes.MESSAGE)
                  .withText("Alica: " + randomResponse());
        } else {
          return new Activity()
                  .withType(ActivityTypes.MESSAGE)
                  .withText("Alica: " + activity.text());
        }
      }
    } 
      return new Activity()
          .withType(ActivityTypes.MESSAGE)
          .withText("Error. Activity is null");
  }
}

