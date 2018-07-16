package com.epam.chatbotserver.bot_actions;


import com.epam.chatbotserver.models.Reply;
import com.epam.chatbotserver.service.impl.ReplyService;
import com.epam.chatbotserver.utility.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Victor on 06.07.2018.
 * Reply for None bot filed requests
 */
@Service
public class NoneAction implements Action {


    private ReplyService replyService;

    public NoneAction(ReplyService replyService) {
        this.replyService = replyService;
    }

    @Override
    public void action(UserDetails userDetails) {
        List<Reply> replies = replyService.getReplyesForIntent(userDetails);
        int i = (int)(Math.random()*replies.size() - 1);
        userDetails.setReply(replies.get(i));
    }
}
