package com.epam.chatbotserver.bot_actions;

import com.epam.chatbotserver.models.Reply;
import com.epam.chatbotserver.utility.IntentTypes;
import com.epam.chatbotserver.utility.UserDetails;


public class RequestArticleAction implements Action  {

    @Override
    public void action(UserDetails userDetails) {
        Reply reply;
        if(userDetails.getReply().isConfirmed()){
            reply = new Reply("Please enter params for search in form param:value", IntentTypes.GET_ARTICLE_PARAMS);
        } else {
            reply = new Reply("Did i understand you correctly, you want get some Article from KB?", IntentTypes.CONFIRMATION);
        }
        userDetails.setReply(reply);
    }
}