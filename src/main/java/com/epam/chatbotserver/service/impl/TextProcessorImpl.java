package com.epam.chatbotserver.service.impl;


import com.epam.chatbotserver.luis.Entity;
import com.epam.chatbotserver.luis.LuisResponse;
import com.epam.chatbotserver.repository.ReplyRepository;
import com.epam.chatbotserver.service.TextProcessor;
import com.epam.chatbotserver.utility.IntentTypes;
import com.epam.chatbotserver.utility.Urls;
import com.epam.chatbotserver.utility.UserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Victor on 04.07.2018.
 * Зробимо в четвер
 */
@Component
public class TextProcessorImpl implements TextProcessor {



    @Autowired
    private ObjectMapper objectMapper;



    /**
     * This method uses LUIS to perform Language Understanding actions
     * based on it`s reply we decide what intent does user have
     *
     * @return
     */

    @Override
    public boolean decide(UserDetails userDetails) {
        String question = userDetails.getActivity().text();
        LuisResponse response;
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = createRequest(question).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        response = deserializeResponse(jsonResponse.getBody().toString());
        IntentTypes intent = IntentTypes.valueOf(response.getTopScoringIntent().getIntent().toUpperCase());

        if(intent.equals(IntentTypes.SEARCH)) {
            Map<String, String> requeuestParams = getRequestParams(response);
            if(requeuestParams.isEmpty()){
                userDetails.getUser().setIntentType(IntentTypes.GET_ARTICLE);
            }else {
                userDetails.getUser().setIntentType(IntentTypes.GET_ARTICLE_PARAMS);
                userDetails.getReply().setRequestParams(requeuestParams);
            }
        }else if(intent.equals(IntentTypes.CONFIRMATION)) {
            userDetails.getReply().setConfirmed(true);
        }else {
            userDetails.getUser().setIntentType(IntentTypes.valueOf(response.getTopScoringIntent().getIntent().toUpperCase()));
        }
//
//        if (userDetails.getReply().getRequestParams().size() > 0) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }


    private Map<String, String> getRequestParams(LuisResponse response) {
        Map<String, String> requestParams = new HashMap<>(4);
        StringBuilder city = new StringBuilder();
        StringBuilder space = new StringBuilder();
        StringBuilder objName = new StringBuilder();
        StringBuilder author = new StringBuilder();
        for (Entity entity : response.getEntities()) {
            if (entity.getType().equals("Places.AbsoluteLocation")) {
                city.append(entity.getEntity()).append(" ");
            }
            if (entity.getType().equals("Space")) {
                space.append(entity.getEntity());
            }
            if (entity.getType().equals("Article")) {
                objName.append(entity.getEntity()).append(" ");
            }
            if (entity.getType().equals("Author")) {
                author.append(entity.getEntity()).append(" ");
            }
        }

        if (city.length() > 0) {
            requestParams.put("City", deleteLastSpace(city).toString());
        }
        if (space.length() > 0) {
            requestParams.put("Space", space.toString());
        }
        if (objName.length() > 0) {
            requestParams.put("Post", deleteLastSpace(objName).toString());
        }
        if (author.length() > 0) {
            requestParams.put("Author", deleteLastSpace(author).toString());
        }

        return requestParams;
    }

    private StringBuilder deleteLastSpace(StringBuilder s) {
        if (s.charAt(s.length() - 1) == ' ') {
            s = s.deleteCharAt(s.length() - 1);
        }
        return s;
    }

    /**
     * Method creates new request to LUIS API
     *
     * @param question - user's question
     * @return
     */
    private HttpRequest createRequest(String question) {
        HttpRequest request = Unirest.get(Urls.RECOGNISION
                .getUrl())
                .queryString("q", question);
        return request;
    }

    /**
     * Method constructs new LuisResponse object from json
     *
     * @param body json string, which if obtained from LUIS
     * @return
     */
    private LuisResponse deserializeResponse(String body) {
        try {
            return objectMapper.readValue(body, LuisResponse.class);
        } catch (IOException e) {
            return null;
        }
    }

}
