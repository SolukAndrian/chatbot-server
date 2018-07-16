package com.epam.chatbotserver.utility;


import com.epam.chatbotserver.models.Reply;
import com.epam.chatbotserver.models.User;
import com.microsoft.bot.connector.ConnectorClient;
import com.microsoft.bot.connector.implementation.ConnectorClientImpl;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.rest.credentials.ServiceClientCredentials;

/**
 * Created by Victor on 05.07.2018.
 * Class that contains all user-related data during communication
 */
public class UserDetails {

    private Activity activity; // Activity which user sends to chat bot
    private User user; // Obvious user class ;)
    private Reply reply;

    public UserDetails(Activity activity, User user) {
        this.user = user;
        this.activity = activity;
        reply = new Reply();
    }



    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
