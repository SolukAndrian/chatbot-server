package com.epam.chatbotserver.service;

import com.epam.chatbotserver.utility.UserDetails;
import com.microsoft.bot.schema.models.Activity;

public interface BotService {
    Activity interactWithBot(UserDetails userDetails);
}
