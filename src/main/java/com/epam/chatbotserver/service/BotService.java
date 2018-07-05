package com.epam.chatbotserver.service;

import com.microsoft.bot.schema.models.Activity;

public interface BotService {
    public void interactWithBot(Activity activity);
}
