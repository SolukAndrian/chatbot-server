package com.epam.chatbotserver.service.impl;

import com.epam.chatbotserver.service.BotService;
import com.microsoft.bot.connector.ConnectorClient;
import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.connector.implementation.ConnectorClientImpl;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImpl implements BotService {
    private static final String appId = "";       // <-- app id -->
    private static final String appPassword = ""; // <-- app password -->
    private final MicrosoftAppCredentials credentials;

    public BotServiceImpl() {
        this.credentials = new MicrosoftAppCredentials(appId, appPassword);
    }

    @Override
    public void interactWithBot(Activity activity) {
        if (activity != null) {
            if (activity.type().equals(ActivityTypes.MESSAGE)) {
                ConnectorClient connector = new ConnectorClientImpl(activity.serviceUrl(), this.credentials);
                connector.conversations().sendToConversation(activity.conversation().id(),
                        new Activity()
                                .withType(ActivityTypes.MESSAGE)
                                .withText("Alica: " + activity.text())
                                .withRecipient(activity.from())
                                .withFrom(activity.recipient())
                );
            }
        }
    }
}
