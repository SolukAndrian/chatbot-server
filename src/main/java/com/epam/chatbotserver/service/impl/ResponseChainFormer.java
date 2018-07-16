package com.epam.chatbotserver.service.impl;



import com.epam.chatbotserver.bot_actions.*;
import com.epam.chatbotserver.utility.IntentTypes;
import com.epam.chatbotserver.utility.UserDetails;

import java.util.HashMap;
import java.util.Map;


public class ResponseChainFormer {

    private Map<IntentTypes, Action> reactions = new HashMap<>();

    public ResponseChainFormer(ReplyService replyService) {
        reactions.put(IntentTypes.GET_ARTICLE_PARAMS, new ArticleParametersAction(replyService));
        reactions.put(IntentTypes.GET_ARTICLE, new RequestArticleAction());
        reactions.put(IntentTypes.HELLO, new WelcomeAction(replyService));
        reactions.put(IntentTypes.NONE, new NoneAction(replyService));
    }

    public void formAnswer(UserDetails userDetails) {
        System.out.println(userDetails.getUser().getIntentType().name());
        reactions.get(userDetails.getUser().getIntentType()).action(userDetails);
    }
}
