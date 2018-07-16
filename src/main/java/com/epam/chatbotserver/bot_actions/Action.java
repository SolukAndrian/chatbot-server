package com.epam.chatbotserver.bot_actions;

import com.epam.chatbotserver.utility.UserDetails;


public interface Action {

    void action(UserDetails userDetails);
}
