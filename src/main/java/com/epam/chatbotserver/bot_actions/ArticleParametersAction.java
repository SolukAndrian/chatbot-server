package com.epam.chatbotserver.bot_actions;

import com.epam.chatbotserver.models.Reply;
import com.epam.chatbotserver.service.impl.ReplyService;
import com.epam.chatbotserver.utility.IntentTypes;
import com.epam.chatbotserver.utility.UserDetails;

public class ArticleParametersAction implements  Action {


    private static final int MAX_NUMBER_OF_PARAMS = 4;

    private ReplyService replyService;


    public ArticleParametersAction(ReplyService replyService) {
        this.replyService = replyService;
    }

    @Override
    public void action(UserDetails userDetails) {
        Reply reply;
        boolean confirmed = userDetails.getReply().isConfirmed();
        if (confirmed) {
            userDetails.getUser().setIntentType(IntentTypes.CONFIRMATION);
            replyService.formalReplyForIntent(userDetails);
            userDetails.getUser().setIntentType(IntentTypes.GET_ARTICLE);
            //TODO Getting Article from KB in writing it to reply
            reply = new Reply("This is Article you searched for", IntentTypes.GET_ARTICLE);

        } else {
            if (userDetails.getReply().getRequestParams().size() != 0) {
                StringBuilder replyText = new StringBuilder();
                replyText.append("You want to make search by this params:  ");
                for(String s: userDetails.getReply().getRequestParams().keySet()){
                    replyText.append(s);
                    replyText.append(" : ");
                    replyText.append(userDetails.getReply().getRequestParams().get(s));
                    replyText.append("\n");
                }
                replyText.append("?\nEnter next params in way paramName:value if you want to add more.\n");
                replyText.append("Or confirm search by typing 'yes'");
                reply = new Reply(replyText.toString(),
                        IntentTypes.GET_ARTICLE_PARAMS);
            } else {
                reply = new Reply("Please enter params for search in form param:value", IntentTypes.GET_ARTICLE_PARAMS);
            }
        }
        userDetails.setReply(reply);
    }

}
