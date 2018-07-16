package com.epam.chatbotserver.service.impl;

import com.epam.chatbotserver.models.Reply;
import com.epam.chatbotserver.repository.ReplyRepository;
import com.epam.chatbotserver.utility.UserDetails;
import com.microsoft.bot.connector.models.ErrorResponseException;
import com.microsoft.bot.schema.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Victor on 06.07.2018.
 */
@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;


    public void formalReplyForIntent(UserDetails userDetails) {
        List<Reply> replies = replyRepository.findAllByIntentType(userDetails.getUser().getIntentType());
        int i = (int)(Math.random()*replies.size());

        System.out.println("USER INTENT IN REPLY SERVICE  "+ userDetails.getUser().getIntentType().name());
//        Activity reply = ActivityFactory.createReply(userDetails.getActivity(), replies.get(i).getReply());
        try {
            /**
             * And send it to client
             */
            //userDetails.getConnectorInstance().conversations().sendToConversation(userDetails.getActivity().conversation().id(), reply);
        } catch (ErrorResponseException ex) {
            System.out.println("Failed to handle message");
        }
    }

    public List<Reply> getReplyesForIntent(UserDetails userDetails){

        return replyRepository.findAllByIntentType(userDetails.getUser().getIntentType());

    }
}
