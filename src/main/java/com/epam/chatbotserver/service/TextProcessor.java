package com.epam.chatbotserver.service;


import com.epam.chatbotserver.utility.UserDetails;

/**
 * Created by Victor on 04.07.2018.
 */
public interface TextProcessor {

    boolean decide(UserDetails userDetails);



}
