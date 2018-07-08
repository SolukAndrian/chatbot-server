package com.epam.chatbotserver.service;

import com.microsoft.bot.schema.models.Activity;

public interface BotService {
    Activity interactWithBot(Activity activity);
}
