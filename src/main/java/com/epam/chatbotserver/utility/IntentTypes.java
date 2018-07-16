package com.epam.chatbotserver.utility;

import java.io.Serializable;

/**
 * Created by Victor on 05.07.2018.
 * Types of  actions that user can have during communication with chat bot
 * And it used  as recognission sign of LUIS jsons
 */
public enum IntentTypes implements Serializable {

    HELLO,
    CONFIRMATION,
    NONE,
    NEGATION,
    GET_ARTICLE,
    GET_ARTICLE_PARAMS,
    SEARCH,
}
