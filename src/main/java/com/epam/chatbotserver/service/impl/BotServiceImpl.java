package com.epam.chatbotserver.service.impl;

import com.epam.chatbotserver.repository.ReplyRepository;
import com.epam.chatbotserver.service.BotService;
import com.epam.chatbotserver.service.TextProcessor;
import com.epam.chatbotserver.utility.UserCache;
import com.epam.chatbotserver.utility.UserDetails;
import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.connector.models.ErrorResponseException;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BotServiceImpl implements BotService {

  private static final String APP_ID = "";
  private static final String APP_PASSWORD = "";
  private final MicrosoftAppCredentials credentials;


  @Autowired
  private TextProcessor textProcessor;

  @Autowired
  private ReplyService replyService;

  @Autowired
  private ReplyRepository replyRepository;

  private ResponseChainFormer responseChainFormer;

  private boolean inited;

  public BotServiceImpl() {
    this.credentials = new MicrosoftAppCredentials(APP_ID, APP_PASSWORD);
  }

  private void init(){
      responseChainFormer = new ResponseChainFormer(replyService);
      inited = true;
  }

  @Override
  public Activity interactWithBot(UserDetails userDetails) {
      if(!inited){
          init();
      }
    if (userDetails.getActivity() != null) {
      if (userDetails.getActivity().type().equals(ActivityTypes.MESSAGE)) {
        /**
         * Text processor will analyze request and decide what answer give to customer
         */
        textProcessor.decide(userDetails);
        /**
         * Save User state to Cache
         */
        /**
         * Performing actions that needed
         */
        responseChainFormer.formAnswer(userDetails);

        UserCache userCache = UserCache.getInstance();
        try {
          userCache.putObject(userDetails.getUser());
        } catch (IOException e) {
          e.printStackTrace();
        }


        System.out.println("Sending Reply:  "+ userDetails.getReply().getReply());
        try {
          return ActivityFactory.createReply(userDetails.getActivity(),userDetails.getReply().getReply());
        } catch (ErrorResponseException ex) {
          System.out.println("Failed to handle message");
        }
      }
    } 
    return new Activity()
          .withType(ActivityTypes.MESSAGE)
          .withText("Error. Activity is null");
  }
}

