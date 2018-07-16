package com.epam.chatbotserver.service.impl;

/**
 * Created by Victor on 04.07.2018.
 */


import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import org.joda.time.DateTime;

/**
 * Factory class to produce various activities
 */
public final class ActivityFactory {
    /**
     * Creates a new instance of {@link ActivityFactory}
     */
    private ActivityFactory() {

    }

    /**
     * Creates a activity for a reply
     *
     * We form new activity, which bot will send back to a user
     *
     * @param activity Activity which user send to us
     * @param text     Text for the reply
     * @return Returns Activity which must be send to user
     */
    public static Activity createReply(Activity activity, String text) {
        Activity reply = new Activity();

        reply.withFrom(activity.recipient())
                .withRecipient(activity.from())
                .withConversation(activity.conversation())
                .withChannelId(activity.channelId())
                .withReplyToId(activity.id())
                .withServiceUrl(activity.serviceUrl())
                .withTimestamp(new DateTime())
                .withType(ActivityTypes.MESSAGE);


        if (text != null && !text.isEmpty()) {
            reply.withText(text);
        }

        return reply;
    }


}
